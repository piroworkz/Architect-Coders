package com.piroworkz.architectcoders.data

import com.google.common.truth.Truth.assertThat
import com.piroworkz.architectcoders.data.GeoPostalRepository.Companion.DEFAULT_REGION
import com.piroworkz.architectcoders.data.source.LocationDataSource
import com.piroworkz.architectcoders.data.source.PermissionChecker
import com.piroworkz.architectcoders.data.source.PostalCodesDataSource
import com.piroworkz.architectcoders.domain.PostalAddress
import com.piroworkz.architectcoders.testshared.postalAddressList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GeoPostalRepositoryTest {

    @Mock
    lateinit var postalCodesDataSource: PostalCodesDataSource

    @Mock
    lateinit var locationDataSource: LocationDataSource

    @Mock
    lateinit var permissionChecker: PermissionChecker

    private lateinit var geoPostalRepository: GeoPostalRepository

    @Before
    fun setUp() {
        geoPostalRepository = GeoPostalRepository(
            postalCodesDataSource,
            locationDataSource,
            permissionChecker
        )
    }

    @Test
    fun `returns a list of Postal Address filtered by zip code`(): Unit = runTest {
        val postalAddressList: List<PostalAddress> = postalAddressList
        whenever(postalCodesDataSource.requestAddressByZipCode("", DEFAULT_REGION))
            .thenReturn(postalAddressList)

        val request: List<PostalAddress> = geoPostalRepository.requestAddressByZipCode("")!!

        assertThat(request).isEqualTo(postalAddressList)
    }

    @Test
    fun `returns null if zip code is not found in region`(): Unit = runTest {
        whenever(postalCodesDataSource.requestAddressByZipCode("", DEFAULT_REGION))
            .thenReturn(null)

        val request = geoPostalRepository.requestAddressByZipCode("")

        assertThat(request).isNull()
    }

}


