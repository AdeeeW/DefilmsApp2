package com.adewijayanto.defilmsapp2.utils

import com.adewijayanto.defilmsapp2.BuildConfig
import com.adewijayanto.defilmsapp2.data.source.localentity.MovieEntity
import com.adewijayanto.defilmsapp2.data.source.localentity.TvShowEntity

const val IMAGE_URL = BuildConfig.IMAGE_URL

object DataDummy {
    fun generateDummyMovies(): List<MovieEntity> {
        val movies = ArrayList<MovieEntity>()

        movies.add(
            MovieEntity(
                "Raj is a rich, carefree, happy-go-lucky second generation NRI. Simran is the daughter of Chaudhary Baldev Singh, who in spite of being an NRI is very strict about adherence to Indian values. Simran has left for India to be married to her childhood fiancé. Raj leaves for India with a mission at his hands, to claim his lady love under the noses of her whole family. Thus begins a saga.",
                "hi",
                "दिलवाले दुल्हनिया ले जायेंगे",
                false,
                "Dilwale Dulhania Le Jayenge",
                emptyList(),
                emptyList(),
                "$IMAGE_URL/2CAL2433ZeIihfX1Hb2139CX0pW.jpg",
                "$IMAGE_URL/xRI636TOdS1K1GBqIBRSmfZ1T5x.jpg",
                "1995-10-20",
                30.735,
                8.7F,
                19404,
                false,
                2836
            )
        )
        movies.add(
            MovieEntity(
                "Professor Gabriel Emerson finally learns the truth about Julia Mitchell's identity, but his realization comes a moment too late. Julia is done waiting for the well-respected Dante specialist to remember her and wants nothing more to do with him. Can Gabriel win back her heart before she finds love in another's arms?",
                "en",
                "Gabriel's Inferno Part II",
                false,
                "Gabriel's Inferno Part II",
                emptyList(),
                emptyList(),
                "$IMAGE_URL/x5o8cLZfEXMoZczTYWLrUo1P7UJ.jpg",
                "$IMAGE_URL/xRI636TOdS1K1GBqIBRSmfZ1T5x.jpg",
                "2020-07-31",
                12.613,
                8.7F,
                724089,
                false,
                1240
            )
        )
        movies.add(
            MovieEntity(
                "Framed in the 1940s for the double murder of his wife and her lover, upstanding banker Andy Dufresne begins a new life at the Shawshank prison, where he puts his accounting skills to work for an amoral warden. During his long stretch in prison, Dufresne comes to be admired by the other inmates -- including an older prisoner named Red -- for his integrity and unquenchable sense of hope.",
                "en",
                "The Shawshank Redemption",
                false,
                "The Shawshank Redemption",
                emptyList(),
                emptyList(),
                "$IMAGE_URL/q6y0Go1tsGEsmtFryDOJo3dEmqu.jpg",
                "$IMAGE_URL/iNh3BivHyg5sQRPP1KOkzguEX0H.jpg",
                "1994-09-23",
                40.104,
                8.7F,
                278,
                false,
                18799
            )
        )
        movies.add(
            MovieEntity(
                "Spanning the years 1945 to 1955, a chronicle of the fictional Italian-American Corleone crime family. When organized crime family patriarch, Vito Corleone barely survives an attempt on his life, his youngest son, Michael steps in to take care of the would-be killers, launching a campaign of bloody revenge.",
                "en",
                "The Godfather",
                false,
                "The Godfather",
                emptyList(),
                emptyList(),
                "$IMAGE_URL/x5o8cLZfEXMoZczTYWLrUo1P7UJ.jpg",
                "$IMAGE_URL/iNh3BivHyg5sQRPP1KOkzguEX0H.jpg",
                "1972-03-14",
                45.469,
                8.7F,
                238,
                false,
                14196
            )
        )
        movies.add(
            MovieEntity(
                "The final part of the film adaption of the erotic romance novel Gabriel's Inferno written by an anonymous Canadian author under the pen name Sylvain Reynard.",
                "en",
                "Gabriel's Inferno Part III",
                false,
                "Gabriel's Inferno Part III",
                emptyList(),
                emptyList(),
                "$IMAGE_URL/fYtHxTxlhzD4QWfEbrC1rypysSD.jpg",
                "$IMAGE_URL/iNh3BivHyg5sQRPP1KOkzguEX0H.jpg",
                "2020-11-19",
                45.469,
                8.7F,
                761053,
                false,
                799
            )
        )
        return movies
    }

    fun generateDummyTvShow(): List<TvShowEntity> {
        val tvShow = ArrayList<TvShowEntity>()

        tvShow.add(
            TvShowEntity(
                "2004-05-10",
                "I Am Not An Animal is an animated comedy series about the only six talking animals in the world, whose cosseted existence in a vivisection unit is turned upside down when they are liberated by animal rights activists.",
                "en",
                emptyList(),
                emptyList(),
                "$IMAGE_URL/qG59J1Q7rpBc1dvku4azbzcqo8h.jpg",
                listOf("GB"),
                "I Am Not an Animal",
                20.194,
                9.4F,
                "I Am Not an Animal",
                100,
                615
            )
        )
        tvShow.add(
            TvShowEntity(
                "2004-05-10",
                "Tightly clutching his Gibson guitar, Mafuyu Satou steps out of his dark apartment to begin another day of his high school life. While taking a nap in a quiet spot on the gymnasium staircase, he has a chance encounter with fellow student Ritsuka Uenoyama, who berates him for letting his guitar's strings rust and break. Noticing Uenoyama's knowledge of the instrument, Satou pleads for him to fix it and to teach him how to play. Uenoyama eventually agrees and invites him to sit in on a jam session with his two band mates: bassist Haruki Nakayama and drummer Akihiko Kaji.",
                "ja",
                emptyList(),
                emptyList(),
                "$IMAGE_URL/pdDCcAq8RNSZNk81PXYoHNUPHjn.jpg",
                listOf("JP"),
                "ギヴン",
                20.194,
                9.2F,
                "Given",
                88040,
                440
            )
        )
        tvShow.add(
            TvShowEntity(
                "2019-01-11",
                "Surrounded by a forest and a gated entrance, the Grace Field House is inhabited by orphans happily living together as one big family, looked after by their \"Mama,\" Isabella. Although they are required to take tests daily, the children are free to spend their time as they see fit, usually playing outside, as long as they do not venture too far from the orphanage — a rule they are expected to follow no matter what. However, all good times must come to an end, as every few months, a child is adopted and sent to live with their new family... never to be heard from again.\n\nHowever, the three oldest siblings have their suspicions about what is actually happening at the orphanage, and they are about to discover the cruel fate that awaits the children living at Grace Field, including the twisted nature of their beloved Mama.",
                "ja",
                emptyList(),
                emptyList(),
                "$IMAGE_URL/oBgRCpAbtMpk1v8wfdsIph7lPQE.jpg",
                listOf("JP"),
                "約束のネバーランド",
                79.434,
                9.1F,
                "The Promised Neverland",
                83097,
                610
            )
        )
        tvShow.add(
            TvShowEntity(
                "2019-01-09",
                "Iwatani Naofumi was summoned into a parallel world along with 3 other people to become the world's Heroes. Each of the heroes respectively equipped with their own legendary equipment when summoned, Naofumi received the Legendary Shield as his weapon. Due to Naofumi's lack of charisma and experience he's labeled as the weakest, only to end up betrayed, falsely accused, and robbed by on the third day of adventure. Shunned by everyone from the king to peasants, Naofumi's thoughts were filled with nothing but vengeance and hatred. Thus, his destiny in a parallel World begins...",
                "en",
                emptyList(),
                emptyList(),
                "$IMAGE_URL/6cXf5EDwVhsRv8GlBzUTVnWuk8Z.jpg",
                listOf("JP"),
                "盾の勇者の成り上がり",
                20.194,
                9.1F,
                "The Rising of the Shield Hero",
                83095,
                508
            )
        )
        tvShow.add(
            TvShowEntity(
                "2014-10-10",
                "Kousei Arima was a genius pianist until his mother's sudden death took away his ability to play. Each day was dull for Kousei. But, then he meets a violinist named Kaori Miyazono who has an eccentric playing style. Can the heartfelt sounds of the girl's violin lead the boy to play the piano again?",
                "ja",
                emptyList(),
                emptyList(),
                "$IMAGE_URL/IGbeFv5Ji4W0x530JcSHiQpamY.jpg",
                listOf("JP"),
                "四月は君の嘘",
                33.962,
                9F,
                "Your Lie in April",
                61663,
                461
            )
        )
        return tvShow
    }
}