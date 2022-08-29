package com.piroworkz.architectcoders.data

import com.google.common.truth.Truth.assertThat
import com.piroworkz.architectcoders.data.source.AuthenticationDatasource
import com.piroworkz.architectcoders.data.source.UserLocalDataSource
import com.piroworkz.architectcoders.data.source.UserRemoteDataSource
import com.piroworkz.architectcoders.domain.Error
import com.piroworkz.architectcoders.domain.UserProfile
import com.piroworkz.architectcoders.testshared.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class UserRepositoryTest {

    @Mock
    lateinit var remoteDataSource: UserRemoteDataSource

    @Mock
    lateinit var localDataSource: UserLocalDataSource

    @Mock
    lateinit var authDatasource: AuthenticationDatasource

    private lateinit var repository: UserRepository
    private val userProfileFlow: Flow<UserProfile> = flowOf(userProfileFull)

    @Before
    fun setUp() {
        whenever(localDataSource.userProfile).thenReturn(userProfileFlow)
        repository = UserRepository(remoteDataSource, localDataSource, authDatasource)
    }

    @Test
    fun `returns user's profile as flow from local database`() {
        assertThat(repository.userProfile).isEqualTo(userProfileFlow)
    }

    @Test
    fun `returns user's id from local database`(): Unit = runTest {
        val id: String = userProfileFull.user?.email!!
        whenever(localDataSource.userId()).thenReturn(id)

        assertThat(repository.getUserId()).isEqualTo(id)
    }

    @Test
    fun `saves user profile to local database`(): Unit = runTest {
        val userProfile: UserProfile = userProfileFull
        whenever(remoteDataSource.getUserProfile(any())).thenReturn(userProfile)

        val result: Error? = repository.requestUserProfile("")
        val remoteResult: UserProfile? = remoteDataSource.getUserProfile("")

        println(remoteResult)
        println(result)

        verify(localDataSource).saveUserProfile(userProfile)
        assertThat(result).isEqualTo(null)
    }

    @Test
    fun `saves shipping address to local database`(): Unit = runTest {
        val shippingAddress = shippingAddress
        whenever(remoteDataSource.saveShippingAddress(any())).thenReturn(null)
        whenever(remoteDataSource.downloadShippingAddress(any())).thenReturn(shippingAddress)

        val error: Error? = repository.saveShippingAddress(shippingAddress)

        verify(localDataSource).saveShippingAddress(shippingAddress)
    }

    @Test
    fun `saves billing address to local database`(): Unit = runTest {
        val billingAddress = billingAddress
        whenever(remoteDataSource.saveBillingAddress(any())).thenReturn(null)
        whenever(remoteDataSource.downloadBillingAddress(any())).thenReturn(billingAddress)

        repository.saveBillingAddress(billingAddress)

        verify(localDataSource).saveBillingAddress(billingAddress)
    }

    @Test
    fun `save payment method to local database`(): Unit = runTest {
        val paymentMethod = paymentMethod
        whenever(remoteDataSource.savePaymentMethod(any())).thenReturn(null)
        whenever(remoteDataSource.downloadPaymentMethod(any())).thenReturn(paymentMethod)

        repository.savePaymentMethod(paymentMethod)

        verify(localDataSource).savePaymentMethod(paymentMethod)
    }

    @Test
    fun `get user profile info from firebaseAuth`(): Unit = runTest {
        val user = user
        whenever(authDatasource.getUserFromFirebase()).thenReturn(user)

        assertThat(repository.getUserFromFirebase()).isEqualTo(user)
    }

    @Test
    fun `saves a new user account to local database`(): Unit = runTest {
        val userProfile = userProfileFull
        whenever(remoteDataSource.userExists(any())).thenReturn(false)
        whenever(remoteDataSource.createNewUserAccount(any())).thenReturn(null)
        whenever(remoteDataSource.getUserProfile(any())).thenReturn(userProfile)

        repository.createNewUserAccount(userProfile.user!!)

        verify(localDataSource).saveUser(userProfile)
    }

    @Test
    fun `sign user out if profile has been deleted from database`(): Unit = runTest {
        whenever(localDataSource.deleteUserProfile()).thenReturn(null)

        repository.signOut()

        verify(authDatasource).signOut()
    }

    @Test
    fun `returns true if FirebaseAuth provider is facebook`() {
        whenever(authDatasource.checkAuthProvider()).thenReturn(true)

        assertThat(repository.checkAuthProvider()).isTrue()
    }
}