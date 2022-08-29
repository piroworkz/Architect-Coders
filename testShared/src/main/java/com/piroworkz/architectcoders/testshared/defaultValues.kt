package com.piroworkz.architectcoders.testshared

import com.piroworkz.architectcoders.domain.*

val user = User(
    "David Luna",
    "voxel.mail@gmail.com",
    "user id",
    "",
    ""
)

val shippingAddress = ShippingAddress(
    user.email,
    "street",
    "Santa Clara",
    "Lerma",
    "Estado de México",
    "52004"
)

val billingAddress = BillingAddress(
    user.email,
    "street",
    "Santa Clara",
    "Lerma",
    "Estado de México",
    "52004"
)

val paymentMethod = PaymentMethod(
    user.email,
    "card holder",
    "card number",
    "card issuer",
    "card expiration",
    "card cvc"
)

val userProfileFull = UserProfile(
    user, shippingAddress, billingAddress, paymentMethod
)

val userProfileNew = UserProfile(
    user, null, null, null
)

val postalAddress = PostalAddress(
    "52004",
    "Estado de México",
    "Lerma",
    "Santa Clara"
)

val postalAddressList = listOf(
    postalAddress,
    postalAddress.copy(town = "Santa Rosa"),
    postalAddress.copy(town = "Cedros 4000")
)

val productListOf = listOf(
    Product(
        model = "ars_03052022_charlie.png",
        imageUrl =
        "https://firebasestorage.googleapis.com/v0/b/projectmb-f7d66.appspot.com/o/Products%2Fars_03052022_charlie.png?alt=media&token=4c4c9b37-a744-42ee-96c0-9c8b625e4735",
        mediumSize = 10, smallSize = 10, largeSize = 10,
        description = "Playera color navy ,100% algodón, Charles \"Charlie\" Brown es el personaje principal de la tira cómica Peanuts, distribuida en periódicos diarios y dominicales en numerosos países de todo el mundo. Representado como un \"perdedor adorable\", Charlie Brown es uno de los grandes arquetipos estadounidenses y un personaje de dibujos animados popular y ampliamente reconocido.",
        price = 250,
        title = "Cholo Brown", favorite = false
    ),
    Product(
        model = "ars_03052022_hands.png",
        title = "Love and Hate",
        mediumSize = 10,
        largeSize = 10,
        smallSize = 10,
        description = "Playera color negro ,100% algodón. ",
        price = 300,
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/projectmb-f7d66.appspot.com/o/Products%2Fars_03052022_hands.png?alt=media&token=ad8f25a2-a3fd-476f-a61c-34cfc9bb7980",
        favorite = false
    ),
    Product(
        model = "ars_03052022_snoopdog.png",
        smallSize = 10, mediumSize = 10, largeSize = 10,
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/projectmb-f7d66.appspot.com/o/Products%2Fars_03052022_snoopdog.png?alt=media&token=f181c767-4787-4bc9-a65d-1210882ff47d",
        description = "Playera color blanco ,100% algodón, Calvin Cordozar Broadus Jr., conocido profesionalmente como Snoop Dogg, es un rapero, compositor, personalidad de los medios, actor y empresario estadounidense. Su fama se remonta a 1992 cuando apareció en el sencillo debut en solitario de Dr. Dre, \"Deep Cover\", y luego en el álbum debut en solitario de Dre, The Chronic. ",
        title = "SnoopyDogg!",
        price = 300, favorite = false
    ),
    Product(
        model = "ars_090422_deathrow.png",
        largeSize = 10,
        mediumSize = 10,
        smallSize = 10,
        price = 300,
        title = "SnoopDog, Deathrow records!",
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/projectmb-f7d66.appspot.com/o/Products%2Fars_090422_deathrow.png?alt=media&token=c2bbe76e-bad9-4943-9316-517adc8e39f5",
        description = "Playera color negro ,100% algodón, Deathrow logo con Snoopdog.",
        favorite = false
    ),
    Product(
        model = "ars_090422_dontbeamenace.png",
        description = "Ashtray, un joven negro, es enviado a vivir al gueto con su padre, donde aprende sobre las realidades de la vida en las calles y cómo convertirse en un hombre de sus amigos y familiares. ",
        title = "Don't be a menace!",
        largeSize = 10, smallSize = 10, mediumSize = 10,
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/projectmb-f7d66.appspot.com/o/Products%2Fars_090422_dontbeamenace.png?alt=media&token=db244831-2081-4c07-ae68-d954c05a13c8",
        price = 300, favorite = false
    ),
    Product(
        model = "ars_090422_dre_em_snoop.png",
        price = 300,
        mediumSize = 10, largeSize = 10, smallSize = 10,
        description = "Playera color negro ,100% algodón, Ilustracion de Snoopdog, Dr. Dre y Eminem.",
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/projectmb-f7d66.appspot.com/o/Products%2Fars_090422_dre_em_snoop.png?alt=media&token=8d89d776-0a52-4fe4-8c0b-6bde40ad2cdd",
        title = "Snoopdog, Dr Dre y Eminem", favorite = false
    ),
    Product(
        model = "ars_090422_eazy.png",
        description = "Playera color negro ,100% algodón, Eric Lynn Wright, conocido profesionalmente como Eazy-E, fue un rapero estadounidense que impulsó el rap de la costa oeste y el gangsta rap al liderar el grupo NWA y su sello, Ruthless Records. A menudo se le conoce como el \"Padrino del Gangsta Rap\". ",
        price = 300,
        mediumSize = 10,
        smallSize = 10,
        largeSize = 10,
        title = "Eazy-E , N.W.A.",
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/projectmb-f7d66.appspot.com/o/Products%2Fars_090422_eazy.png?alt=media&token=0aed2546-b38a-429f-8ea8-9f9b01226d22",
        favorite = false
    ),
    Product(
        model = "ars_090422_menace.png",
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/projectmb-f7d66.appspot.com/o/Products%2Fars_090422_menace.png?alt=media&token=208d38ac-1c47-4554-ba05-10da46b7a692",
        description = "Playera negra 100% algodón. Ashtray, un joven negro, es enviado a vivir al gueto con su padre, donde aprende sobre las realidades de la vida en las calles y cómo convertirse en un hombre de sus amigos y familiares.",
        title = "Don't be a menace!",
        price = 300,
        largeSize = 10, smallSize = 10, mediumSize = 10, favorite = false
    ),
    Product(
        model = "ars_090422_tupac.png",
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/projectmb-f7d66.appspot.com/o/Products%2Fars_090422_tupac.png?alt=media&token=07e2e273-ed7e-46d2-9303-bfdc683ef084",
        title = "Tupac , Trust No One",
        mediumSize = 10, largeSize = 10, smallSize = 10,
        description = "Playera color navy ,100% algodón, upac Amaru Shakur, conocido profesionalmente como 2Pac y más tarde como Makaveli, fue un rapero y actor estadounidense. Es considerado uno de los raperos más influyentes de todos los tiempos. Shakur se encuentra entre los artistas musicales más vendidos, con más de 75 millones de discos vendidos en todo el mundo",
        price = 300, favorite = false
    ),
    Product(
        model = "ars_090422_tupac_fresh.png",
        title = "Tupac , Fresh",
        price = 300,
        description = "Playera color negro ,100% algodón, upac Amaru Shakur, conocido profesionalmente como 2Pac y más tarde como Makaveli, fue un rapero y actor estadounidense. Es considerado uno de los raperos más influyentes de todos los tiempos. Shakur se encuentra entre los artistas musicales más vendidos, con más de 75 millones de discos vendidos en todo el mundo",
        mediumSize = 10,
        largeSize = 10,
        smallSize = 10,
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/projectmb-f7d66.appspot.com/o/Products%2Fars_090422_tupac_fresh.png?alt=media&token=723f5670-33c4-435a-ad87-2038b3f16261",
        favorite = false
    ),
    Product(
        model = "ars_17052022_biggie.png",
        title = "Biggie",
        mediumSize = 10,
        largeSize = 10,
        smallSize = 10,
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/projectmb-f7d66.appspot.com/o/Products%2Fars_17052022_biggie.png?alt=media&token=2490159b-74aa-4947-b0e6-fc8ea7b148f9",
        price = 300,
        description = "Playera color negro ,100% algodón. Christopher George Latore Wallace, más conocido por sus nombres artísticos como Notorious BIG, Biggie Smalls o simplemente Biggie, fue un rapero y compositor estadounidense. Arraigado en la escena del rap de Nueva York y en las tradiciones del rap gangsta, es ampliamente considerado como uno de los mejores raperos de todos los tiempos.",
        favorite = false
    ),
    Product(
        model = "ars_17052022_tupac.png",
        title = "Tupac , Trust No One",
        description = "Playera color negro ,100% algodón, upac Amaru Shakur, conocido profesionalmente como 2Pac y más tarde como Makaveli, fue un rapero y actor estadounidense. Es considerado uno de los raperos más influyentes de todos los tiempos. Shakur se encuentra entre los artistas musicales más vendidos, con más de 75 millones de discos vendidos en todo el mundo",
        price = 300,
        mediumSize = 10,
        smallSize = 10,
        largeSize = 10,
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/projectmb-f7d66.appspot.com/o/Products%2Fars_17052022_tupac.png?alt=media&token=9836c8b7-0ac9-4c2d-8f69-7e8ba4e21d6e",
        favorite = false
    ),
    Product(
        model = "ars_19052022_tupac.png",
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/projectmb-f7d66.appspot.com/o/Products%2Fars_19052022_tupac.png?alt=media&token=5a18fa81-bba8-4e02-bfad-75381156fc97",
        description = "Playera color negro ,100% algodón, upac Amaru Shakur, conocido profesionalmente como 2Pac y más tarde como Makaveli, fue un rapero y actor estadounidense. Es considerado uno de los raperos más influyentes de todos los tiempos. Shakur se encuentra entre los artistas musicales más vendidos, con más de 75 millones de discos vendidos en todo el mundo",
        price = 300,
        title = "2pac",
        largeSize = 10, smallSize = 10, mediumSize = 10, favorite = false
    ),
    Product(
        model = "mb_22_0104_milk.png",
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/projectmb-f7d66.appspot.com/o/Products%2Fmb_22_0104_milk.png?alt=media&token=6382ab86-a46e-46f5-9502-9fdd58e7e0d8",
        title = "Dragon Ball Milk",
        price = 300,
        smallSize = 10,
        largeSize = 10,
        mediumSize = 10,
        description = "Playera color negro ,100% algodón. Dragon Ball es una franquicia de medios japonesa creada por Akira Toriyama en 1984. El manga inicial, escrito e ilustrado por Toriyama, se serializó en Weekly Shōnen Jump de 1984 a 1995, con 519 capítulos individuales recopilados en 42 volúmenes de tankōbon por su editor Shueisha.",
        favorite = false
    ),
    Product(
        model = "mb_22_0104_songoku.png",
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/projectmb-f7d66.appspot.com/o/Products%2Fmb_22_0104_songoku.png?alt=media&token=68581672-22ab-4388-a6df-1a5b94a1b0e1",
        description = "Playera color negro ,100% algodón. Dragon Ball es una franquicia de medios japonesa creada por Akira Toriyama en 1984. El manga inicial, escrito e ilustrado por Toriyama, se serializó en Weekly Shōnen Jump de 1984 a 1995, con 519 capítulos individuales recopilados en 42 volúmenes de tankōbon por su editor Shueisha.",
        largeSize = 10, mediumSize = 10, smallSize = 10,
        title = "Son Goku",
        price = 300, favorite = false
    ),
    Product(
        model = "mb_22_0404_bakugo.png",
        title = "My Hero Academia, Bakugo",
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/projectmb-f7d66.appspot.com/o/Products%2Fmb_22_0404_bakugo.png?alt=media&token=dc534b8f-cfbb-42d9-b38f-89155a4740dd",
        price = 300,
        mediumSize = 10,
        largeSize = 10,
        smallSize = 10,
        description = "Playera color negro ,100% algodón. My Hero Academia es una serie de manga de superhéroes japonesa escrita e ilustrada por Kōhei Horikoshi. La historia sigue a Izuku Midoriya, un niño que nació sin superpoderes en un mundo donde se han convertido en algo común, pero que todavía sueña con convertirse en un superhéroe.",
        favorite = false
    ),
    Product(
        model = "mb_22_0404_chill.png",
        largeSize = 10,
        mediumSize = 10,
        smallSize = 10,
        price = 300,
        title = "Chill, Get high with me",
        description = "Playera color negro ,100% algodón.",
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/projectmb-f7d66.appspot.com/o/Products%2Fmb_22_0404_chill.png?alt=media&token=4cc134f9-96a8-4a80-a78c-5d549634d371",
        favorite = false
    ),
    Product(
        model = "mb_22_0404_nezuko.png",
        description = "Playera color negro ,100% algodón. Demon Slayer=  Kimetsu no Yaiba es una serie de manga japonesa escrita e ilustrada por Koyoharu Gotouge. Sigue al adolescente Tanjiro Kamado, que se esfuerza por convertirse en un asesino de demonios después de que su familia fuera masacrada y su hermana menor, Nezuko, se convirtiera en un demonio.",
        largeSize = 10, smallSize = 10, mediumSize = 10,
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/projectmb-f7d66.appspot.com/o/Products%2Fmb_22_0404_nezuko.png?alt=media&token=9184d3da-bb67-4c2b-a0b4-ced8e5a5fdac",
        title = "Demon Slayer, Nezuko",
        price = 300, favorite = false
    ),
    Product(
        model = "mb_22_0404_tanjiro.png",
        price = 300,
        description = "Playera color negro ,100% algodón. Demon Slayer=  Kimetsu no Yaiba es una serie de manga japonesa escrita e ilustrada por Koyoharu Gotouge. Sigue al adolescente Tanjiro Kamado, que se esfuerza por convertirse en un asesino de demonios después de que su familia fuera masacrada y su hermana menor, Nezuko, se convirtiera en un demonio.",
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/projectmb-f7d66.appspot.com/o/Products%2Fmb_22_0404_tanjiro.png?alt=media&token=129af76b-fff1-4e47-92e2-50811af01ff5",
        mediumSize = 10, smallSize = 10, largeSize = 10,
        title = "Demon Slayer, Tanjiro", favorite = false
    ),
    Product(
        model = "mb_22_0407_puerco_del_mal.png",
        price = 300,
        title = "Demon Slayer, Inozuke",
        description = "Playera color negro ,100% algodón. Demon Slayer=  Kimetsu no Yaiba es una serie de manga japonesa escrita e ilustrada por Koyoharu Gotouge. Sigue al adolescente Tanjiro Kamado, que se esfuerza por convertirse en un asesino de demonios después de que su familia fuera masacrada y su hermana menor, Nezuko, se convirtiera en un demonio.",
        smallSize = 10,
        mediumSize = 10,
        largeSize = 10,
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/projectmb-f7d66.appspot.com/o/Products%2Fmb_22_0407_puerco_del_mal.png?alt=media&token=1f64c4a9-06f4-4816-945a-6c1679d605c9",
        favorite = false
    ),
    Product(
        model = "mb_22_0407_southpark.png",
        description = "Playera color blanco ,100% algodón. Stan, Kyle, Eric y Kenny son cuatro amigos malhablados que viven en South Park. Tienen varias desventuras extrañas en la ciudad y sus alrededores, que involucran tanto lo ordinario como lo sobrenatural. ",
        title = "Southpark",
        largeSize = 10, mediumSize = 10, smallSize = 10,
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/projectmb-f7d66.appspot.com/o/Products%2Fmb_22_0407_southpark.png?alt=media&token=ecb40490-e428-47cc-b0ee-ee357aac9f0d",
        price = 300, favorite = false
    ),
    Product(
        model = "mb_22_0905_snoopdog.png",
        title = "SnoopyDogg!",
        price = 300,
        description = "Playera color negro ,100% algodón. Calvin Cordozar Broadus Jr., conocido profesionalmente como Snoop Dogg, es un rapero, compositor, personalidad de los medios, actor y empresario estadounidense. Su fama se remonta a 1992 cuando apareció en el sencillo debut en solitario de Dr. Dre, \"Deep Cover\", y luego en el álbum debut en solitario de Dre, The Chronic. ",
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/projectmb-f7d66.appspot.com/o/Products%2Fmb_22_0905_snoopdog.png?alt=media&token=ba0147b2-c70f-45da-b334-3001c84ca574",
        largeSize = 10, smallSize = 10, mediumSize = 10, favorite = false
    ),
    Product(
        model = "mb_22_1107_lora.png",
        largeSize = 10,
        smallSize = 10,
        mediumSize = 10,
        description = "Playera color negro ,100% algodón. ",
        title = "Alex Meets Sir Paul",
        price = 300,
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/projectmb-f7d66.appspot.com/o/Products%2Fmb_22_1107_lora.png?alt=media&token=d94c25e8-eddc-406f-b79b-b54945c82c1e",
        favorite = false
    ),
    Product(
        model = "mb_22_1404_restinpeace.png",
        price = 300,
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/projectmb-f7d66.appspot.com/o/Products%2Fmb_22_1404_restinpeace.png?alt=media&token=617dd8a8-60f1-41a1-a19d-27dfc58773bb",
        description = "Playera color negro ,100% algodón. Gangsta rap legends!",
        largeSize = 10, smallSize = 10, mediumSize = 10,
        title = "Tupac & Biggie & Eazy-e", favorite = false
    ),
    Product(
        model = "mb_22_1404_tanzuko.png",
        description = "Playera color blanco ,100% algodón. Demon Slayer=  Kimetsu no Yaiba es una serie de manga japonesa escrita e ilustrada por Koyoharu Gotouge. Sigue al adolescente Tanjiro Kamado, que se esfuerza por convertirse en un asesino de demonios después de que su familia fuera masacrada y su hermana menor, Nezuko, se convirtiera en un demonio.",
        price = 300,
        title = "Demon Slayer, Nezuko & Tanjiro",
        mediumSize = 10,
        largeSize = 10,
        smallSize = 10,
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/projectmb-f7d66.appspot.com/o/Products%2Fmb_22_1404_tanzuko.png?alt=media&token=4ad1a6fd-4093-49be-9955-b59deb6f990b",
        favorite = false
    ),
    Product(
        model = "mb_22_1404_tokyorev.png",
        largeSize = 10,
        mediumSize = 10,
        smallSize = 10,
        title = "Tokyo Revengers",
        price = 300,
        description = "Playera color negro ,100% algodón. Tokyo Revengers es una serie de manga japonesa escrita e ilustrada por Ken Wakui. Se ha serializado en la revista Weekly Shōnen de Kodansha desde marzo de 2017. Una adaptación de la serie de televisión de anime de Liden Films se emitió de abril a septiembre de 2021. Una segunda temporada se estrenará en enero de 2023",
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/projectmb-f7d66.appspot.com/o/Products%2Fmb_22_1404_tokyorev.png?alt=media&token=0ce78b7f-852d-4dae-a7ff-8e7804ddd17d",
        favorite = false
    ),
    Product(
        model = "mb_22_1404_trunks_vegeta.png",
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/projectmb-f7d66.appspot.com/o/Products%2Fmb_22_1404_trunks_vegeta.png?alt=media&token=febcf483-5018-4532-84ec-ab0630151569",
        title = "Dragon Ball Z",
        mediumSize = 10,
        largeSize = 10,
        smallSize = 10,
        price = 300,
        description = "Playera color negro ,100% algodón. Un valiente joven con poderes increíbles se aventura hacia un viaje místico en tierras exóticas llenas de guerreros nobles, princesas hermosas, monstruos mutantes, extraterrestres y crueles ejércitos. ",
        favorite = false
    ),
    Product(
        model = "mb_22_1705_eminem.png",
        price = 300,
        title = "Marshall Matters, Eminem",
        description = "Playera color negro ,100% algodón. Marshall Bruce Mathers III, conocido profesionalmente como Eminem, es un rapero, compositor y productor discográfico estadounidense. Se le atribuye la popularización del hip hop en el centro de Estados Unidos y es aclamado por la crítica como uno de los mejores raperos de todos los tiempos.",
        mediumSize = 10,
        smallSize = 10,
        largeSize = 10,
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/projectmb-f7d66.appspot.com/o/Products%2Fmb_22_1705_eminem.png?alt=media&token=4f03d57e-f5b6-422c-8909-ed4db7bd0998",
        favorite = false
    ),
    Product(
        model = "mb_22_1705_onepiece.png",
        smallSize = 10,
        largeSize = 10,
        mediumSize = 10,
        price = 300,
        title = "One Piece, Monkey D'luffy",
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/projectmb-f7d66.appspot.com/o/Products%2Fmb_22_1705_onepiece.png?alt=media&token=419fcbfe-ea38-4391-82db-b8fe8d387795",
        description = "Playera color negro ,100% algodón. One Piece es un manga escrito e ilustrado por el mangaka japonés Eiichirō Oda. Comenzó a publicarse en la revista japonesa Weekly Shōnen Jump el 22 de julio de 1997 y a la fecha se han publicado 102 volúmenes.",
        favorite = false
    ),
    Product(
        model = "mb_22_1705_thundercats.png",
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/projectmb-f7d66.appspot.com/o/Products%2Fmb_22_1705_thundercats.png?alt=media&token=e8e7cd79-97ba-41a2-81d8-4722a21b0b62",
        description = "Playera color negro ,100% algodón. \"ThunderCats\" sigue las aventuras de un grupo de extraterrestres humanoides parecidos a gatos del planeta Thundera. Cuando el planeta moribundo llega a su fin, el grupo, conocido como ThunderCats, se ve obligado a huir de su tierra natal. Mientras se van en su Thunderfleet, los ThunderCats son atacados por los mutantes de Plun-Darr , que atacan la mayoría de sus naves estelares. El daño significa que no pueden llegar a su destino previsto, por lo que terminan en la Tercera Tierra. Los ThunderCats se hacen amigos de los nativos de la Tercera Tierra, quienes ayudan a los gatos cuando los mutantes descubren dónde están y atacan nuevamente. ",
        largeSize = 10, mediumSize = 10, smallSize = 10,
        title = "Thundercats",
        price = 300, favorite = false
    ),
    Product(
        model = "mb_22_1804_mcmiller.png",
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/projectmb-f7d66.appspot.com/o/Products%2Fmb_22_1804_mcmiller.png?alt=media&token=70f961a9-b107-4914-b103-63b7139d2ea6",
        largeSize = 10, smallSize = 10, mediumSize = 10,
        description = "Playera color negro ,100% algodón. Malcolm James McCormick, conocido profesionalmente como Mac Miller, fue un rapero y productor discográfico estadounidense. Miller comenzó su carrera en la escena hip hop de Pittsburgh en 2007, a la edad de quince años. ",
        title = "MacMiller",
        price = 300, favorite = false
    ),
    Product(
        model = "mb_22_1805_lavoe.png",
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/projectmb-f7d66.appspot.com/o/Products%2Fmb_22_1805_lavoe.png?alt=media&token=a0806d2c-85ff-478d-86ef-6b9c21b5f32c",
        title = "Hector Lavoe",
        smallSize = 10, mediumSize = 10, largeSize = 10,
        description = "Playera color negro ,100% algodón. Héctor Juan Pérez Martínez, más conocido como Héctor Lavoe, fue un cantante de salsa puertorriqueño. Lavoe es considerado posiblemente el mejor y más importante cantante e intérprete en la historia de la música salsera porque ayudó a establecer la popularidad de este género musical en las décadas de los 60, 70 y 80.",
        price = 300, favorite = false
    ),
    Product(
        model = "mb_22_1805_tubig.png",
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/projectmb-f7d66.appspot.com/o/Products%2Fmb_22_1805_tubig.png?alt=media&token=580429b1-7500-45e7-9152-fbf4c31eabf4",
        description = "Playera color negro ,100% algodón. La pelea entre Tupac y Biggie fue fruto de una estrecha amistad entre ambos . La pelea comenzó después de que le dispararon a Tupac en el mismo estudio donde Biggie estaba trabajando en una canción. Tupac tenía la impresión de que Biggie estaba involucrado en el tiroteo y, como resultado, los dos comenzaron a intercambiar insultos después de eso. ",
        price = 300,
        largeSize = 10, mediumSize = 10, smallSize = 10,
        title = "Tupac & Biggie", favorite = false
    ),
    Product(
        model = "mb_22_21032022_dmn_slyr.png",
        description = "Playera color negro ,100% algodón. Demon Slayer=  Kimetsu no Yaiba es una serie de manga japonesa escrita e ilustrada por Koyoharu Gotouge. Sigue al adolescente Tanjiro Kamado, que se esfuerza por convertirse en un asesino de demonios después de que su familia fuera masacrada y su hermana menor, Nezuko, se convirtiera en un demonio.",
        title = "Demon Slayer",
        price = 300,
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/projectmb-f7d66.appspot.com/o/Products%2Fmb_22_21032022_dmn_slyr.png?alt=media&token=17a1a2d0-d6ea-4b47-8d48-e8f85f5b2814",
        mediumSize = 10, largeSize = 10, smallSize = 10, favorite = false
    ),
    Product(
        model = "mb_22_2504_boyz n da hood.png",
        title = "Boyz n the Hood",
        description = "Playera color negro ,100% algodón. Tres amigos de la infancia, Darrin, Tre y Ricky, luchan por sobrellevar las distracciones y los peligros de crecer en un gueto de Los Ángeles. ",
        price = 300,
        largeSize = 10,
        smallSize = 10,
        mediumSize = 10,
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/projectmb-f7d66.appspot.com/o/Products%2Fmb_22_2504_boyz%20n%20da%20hood.png?alt=media&token=3e3518e5-b031-49ec-81ba-7d2380d4d39f",
        favorite = false
    ),
    Product(
        model = "mb_22_2504_compton.png",
        mediumSize = 10,
        largeSize = 10,
        smallSize = 10,
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/projectmb-f7d66.appspot.com/o/Products%2Fmb_22_2504_compton.png?alt=media&token=81ee018e-b6c6-4ede-a414-8a386b959157",
        price = 300,
        title = "Straight Outta Compton",
        description = "Playera color negro ,100% algodón. Un grupo de hip-hop de Compton, California, se ve envuelto en varias controversias debido a sus letras descaradas y su música revolucionaria, lo que desencadena una guerra cultural. ",
        favorite = false
    ),
    Product(
        model = "mb_22_2504_elinicio.png",
        smallSize = 10, largeSize = 10, mediumSize = 10,
        description = "Playera color jaspe ,100% algodón.",
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/projectmb-f7d66.appspot.com/o/Products%2Fmb_22_2504_elinicio.png?alt=media&token=9f91b9d9-5553-4695-b94f-cfb1fe9ba1aa",
        price = 300,
        title = "Santa Fe Klan", favorite = false
    ),
    Product(
        model = "mb_22_2504_encerrado.png",
        largeSize = 10, smallSize = 10, mediumSize = 10,
        description = "Playera color negro ,100% algodón.",
        price = 300,
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/projectmb-f7d66.appspot.com/o/Products%2Fmb_22_2504_encerrado.png?alt=media&token=c8fdc826-6d1a-4026-958b-0e78fab3adf5",
        title = "GeraMx, Encerrado no enterrado", favorite = false
    ),
    Product(
        model = "mb_22_2504_gerardo_mexico.png",
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/projectmb-f7d66.appspot.com/o/Products%2Fmb_22_2504_gerardo_mexico.png?alt=media&token=6bee0718-9905-4c7a-9721-af4cd4671f08",
        price = 300,
        description = "Playera color negro ,100% algodón.",
        title = "GeraMx",
        mediumSize = 10, smallSize = 10, largeSize = 10, favorite = false
    ),
    Product(
        model = "mb_22_2504_selena.png",
        title = "Selena",
        price = 300,
        description = "Playera color negro ,100% algodón. Selena Quintanilla Pérez, conocida como Selena, fue una cantante y diseñadora de moda estadounidense. Llamada la \"Reina de la música tejana\", sus contribuciones a la música y la moda la convirtieron en una de las artistas mexicano-estadounidenses más célebres de finales del siglo XX.",
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/projectmb-f7d66.appspot.com/o/Products%2Fmb_22_2504_selena.png?alt=media&token=c5133677-a840-45a1-8adb-6921dc508448",
        largeSize = 10, smallSize = 10, mediumSize = 10, favorite = false
    ),
    Product(
        model = "mb_22_3005_biggie.png",
        description = "Playera color negro ,100% algodón. Christopher George Latore Wallace, más conocido por sus nombres artísticos como Notorious BIG, Biggie Smalls o simplemente Biggie, fue un rapero y compositor estadounidense. Arraigado en la escena del rap de Nueva York y en las tradiciones del rap gangsta, es ampliamente considerado como uno de los mejores raperos de todos los tiempos.",
        mediumSize = 10, largeSize = 10, smallSize = 10,
        title = "Biggie",
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/projectmb-f7d66.appspot.com/o/Products%2Fmb_22_3005_biggie.png?alt=media&token=1bfd0e4b-0f0f-42aa-b49b-a838ad47c8f8",
        price = 300, favorite = false
    ),
    Product(
        model = "mb_22_3103_nezuko.png",
        price = 300,
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/projectmb-f7d66.appspot.com/o/Products%2Fmb_22_3103_nezuko.png?alt=media&token=b906e663-84ba-4a70-bc2c-0188e2a6ac25",
        title = "Demon Slayer, Nezuko",
        description = "Playera color negro ,100% algodón. Demon Slayer=  Kimetsu no Yaiba es una serie de manga japonesa escrita e ilustrada por Koyoharu Gotouge. Sigue al adolescente Tanjiro Kamado, que se esfuerza por convertirse en un asesino de demonios después de que su familia fuera masacrada y su hermana menor, Nezuko, se convirtiera en un demonio.",
        largeSize = 10, mediumSize = 10, smallSize = 10, favorite = false
    ),
    Product(
        model = "mb_22_3103_rappers.png",
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/projectmb-f7d66.appspot.com/o/Products%2Fmb_22_3103_rappers.png?alt=media&token=2fe36a6f-e323-434e-baf7-27e8b60df530",
        description = "Playera color negro ,100% algodón. Gangsta rap legends!",
        smallSize = 10, largeSize = 10, mediumSize = 10,
        price = 300,
        title = "Gangsta rap legends!",
        favorite = false
    )
)


val bannerList = listOf(

    Banner(
        model = "10% discount flyer.png",
        bannerUrl = "https://firebasestorage.googleapis.com/v0/b/projectmb-f7d66.appspot.com/o/Banners%2F10%25%20discount%20flyer.png?alt=media&token=745fea5d-e7e6-4deb-9c00-d478dda7980a"
    ),
    Banner
        (
        model = "12052022_estrenos_banner.png",
        bannerUrl = "https://firebasestorage.googleapis.com/v0/b/projectmb-f7d66.appspot.com/o/Banners%2F12052022_estrenos_banner.png?alt=media&token=88d9d115-f848-4abf-af19-f3ae0dcd9bba"
    ),
    Banner
        (
        model = "12052022_kids_banner.png",
        bannerUrl = "https://firebasestorage.googleapis.com/v0/b/projectmb-f7d66.appspot.com/o/Banners%2F12052022_kids_banner.png?alt=media&token=752fc0c5-7b45-4d2e-8fe7-81afda9d053a"
    ),
    Banner
        (
        model = "12052022_mens_banner.png",
        bannerUrl = "https://firebasestorage.googleapis.com/v0/b/projectmb-f7d66.appspot.com/o/Banners%2F12052022_mens_banner.png?alt=media&token=82a07043-4504-4594-8035-bdab7dfa5b5a"
    ),
    Banner
        (
        model = "12052022_music_banner.png",
        bannerUrl = "https://firebasestorage.googleapis.com/v0/b/projectmb-f7d66.appspot.com/o/Banners%2F12052022_music_banner.png?alt=media&token=0ea0527e-cec3-45e0-b1b6-fe87a08bdad8"
    ),
    Banner
        (
        model = "12052022_piroworkz_banner.png",
        bannerUrl = "https://firebasestorage.googleapis.com/v0/b/projectmb-f7d66.appspot.com/o/Banners%2F12052022_piroworkz_banner.png?alt=media&token=946c4575-1788-41b2-badc-460fff8a650e"
    ),
    Banner
        (
        model = "12052022_rudas_banner.png",
        bannerUrl = "https://firebasestorage.googleapis.com/v0/b/projectmb-f7d66.appspot.com/o/Banners%2F12052022_rudas_banner.png?alt=media&token=30a7d8bb-037f-4d74-b989-1a692bff9697"
    )
)


val product = productListOf[0]

val favoritesList = listOf(
    product.copy(favorite = true),
    product.copy(model = "1", favorite = false),
    product.copy(model = "2", favorite = true),
    product.copy(model = "3", favorite = false),
    product.copy(model = "4", favorite = true)
)

val switchedList = listOf(
    product.copy(favorite = false),
    product.copy(model = "1", favorite = false),
    product.copy(model = "2", favorite = true),
    product.copy(model = "3", favorite = false),
    product.copy(model = "4", favorite = true)
)

val cartItem = CartItem(
    model = product.model,
    description = product.description,
    price = 300,
    title = product.title,
    imageUrl = product.imageUrl,
    smallSize = 1, mediumSize = 0, largeSize = 0
)

val cartItemList = listOf(
    cartItem,
    cartItem.copy(model = productListOf[1].model),
    cartItem.copy(model = productListOf[1].model)
)