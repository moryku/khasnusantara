package com.um.puak

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.VideoView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.akuarintar.ui.quiz.QuizActivity
import com.richpath.RichPath.OnPathClickListener
import com.um.puak.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    lateinit var mapVideo: VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var mapIndo = findViewById<View>(R.id.mapIndo) as IndonesiaMapView
//        Province.values().forEach {
//            mapIndo.addTitle(Province.Aceh, Province.Aceh.name, Typeface.SANS_SERIF, Color.WHITE)
//        }

        mapIndo.iranPath.setOnPathClickListener(OnPathClickListener { richPath ->
            mapIndo.activeProvince(richPath, withAnimate = true)
            if (richPath.name == Province.Aceh.toString()) {
                startActivity(Intent(this@MainActivity, AcehActivity::class.java))
            } else if (richPath.name == Province.Jawa_Barat.toString()) {
                startActivity(Intent(this@MainActivity, JawaBaratActivity::class.java))
            } else if (richPath.name == Province.Jawa_Timur.toString()) {
                startActivity(Intent(this@MainActivity, JatimActivity::class.java))
            } else if (richPath.name == Province.Sumatera_Barat.toString()) {
                startActivity(Intent(this@MainActivity, SumateraBaratActivity::class.java))
            } else if (richPath.name == Province.Sumatera_Selatan.toString()) {
                startActivity(Intent(this@MainActivity, SumateraSelatanActivity::class.java))
            } else if (richPath.name == Province.Jakarta.toString()) {
                startActivity(Intent(this@MainActivity, JakartaActivity::class.java))
            } else if (richPath.name == Province.Sulawesi_Selatan.toString()) {
                startActivity(Intent(this@MainActivity, SulselActivity::class.java))
            } else if (richPath.name == Province.Jawa_Tengah.toString()) {
                startActivity(Intent(this@MainActivity, JatengActivity::class.java))
            } else if (richPath.name == Province.Kalimantan_Timur.toString()) {
                startActivity(Intent(this@MainActivity, KaltimActivity::class.java))
            } else if (richPath.name == Province.Papua.toString()) {
                startActivity(Intent(this@MainActivity, PapuaActivity::class.java))
            } else {
                if (richPath.name == Province.Sumatera_Utara.toString()) {
                    showAlertDialog(richPath.name, "Angkola Mandailing, Batak, Dairi Pak-Pak, Langkat, Limakawatina, Mandailing, Muna, Nias, Pakpak, Pesisir Natal, Siladang, Simalungun, Toba, Ulu Muara Sipongi.")
                } else if (richPath.name == Province.Riau.toString()) {
                    showAlertDialog(richPath.name, "Bonai, Laut, Melayu, Akit, Talang Mamak, Orang Utan Bonai, Sakai, Riau.")
                } else if (richPath.name == Province.Kepulauan_Riau.toString()) {
                    showAlertDialog(richPath.name, "Laut, Melayu, Siak, Sakai.")
                } else if (richPath.name == Province.Bengkulu.toString()) {
                    showAlertDialog(richPath.name, "Bengkulu, Enggano, Muko-Muko, Pekal, Rejang, Serawai, Suban.")
                } else if (richPath.name == Province.Jambi.toString()) {
                    showAlertDialog(richPath.name, "Batin, Jambi, Kerinci, Anak Dalam/Anak Rimbo, Batin, Kubu, Pedah, Penghulu, Pindah.")
                } else if (richPath.name == Province.Sumatera_Selatan.toString()) {
                    showAlertDialog(richPath.name, "Abung Bunga Mayang, Anak Dalam, Daya, Enim, Lahat, Lintang, Lom, Mapur, Meranjat, Musi, Musi Banyuasin, Musi Sekayu, Palembang, Pasemah, Pedamaran, Pegagan, Rambang Senuling, Ranau, Rawas, Saling, Sekak.")
                } else if (richPath.name == Province.Lampung.toString()) {
                    showAlertDialog(richPath.name, "Lampung, Penghulu, Abung/ Bunga mayang/ sembilan marga/ siwo megou, Belalau, Buay Lima, Krui, Megau Pak Tulang Bawang, Melintang rajabasa-Peminggir mr, Nagarigung, Peminggir semangka/ skala Brak/ Teluk, Pepaduan, Pubian/ Pubian Telu suku/ Pubiyan, Seibatin.")
                } else if (richPath.name == Province.Bangka_Belitung.toString()) {
                    showAlertDialog(richPath.name, "Melayu Bangka Belitung, Ameng Sewang, Lom.")
                } else if (richPath.name == Province.Banten.toString()) {
                    showAlertDialog(richPath.name, "Badui atau Orang Kanekes, Sunda. ")
                } else if (richPath.name == Province.Yogyakarta.toString()) {
                    showAlertDialog(richPath.name, "Jawa.")
                } else if (richPath.name == Province.Jawa_Timur.toString()) {
                    showAlertDialog(richPath.name, "Bawean, Jawa, Osing, Madura, Tengger.")
                } else if (richPath.name == Province.Bali.toString()) {
                    showAlertDialog(richPath.name, "Bali, Bali Aga, Bali Majapahit, Loloan, Nyama Selam, Trunyan.")
                } else if (richPath.name == Province.Nusa_Tenggara_Barat.toString()) {
                    showAlertDialog(richPath.name, "Bajau, Bima, Dompu, Semawa/Sumbawa, Dongo, Mbojo, Kore, Dompu, Mata, Sasak, Sumbawa.")
                } else if (richPath.name == Province.Nusa_Tenggara_Timur.toString()) {
                    showAlertDialog(richPath.name, "Abui, Adabe, Alor, Atoni, Bajau,Bajawa, Bantuk, Belu, Bunak, Dawan, Gaura, Helong, Labala, Lamalohot, Lio, Marae, Maung, Mela, Modo,Muhang, Nage-Keo, Ngada, Noeunleni, Riung, Rongga, Rote, Sabu, Sikka, Sumba, Tetun.")
                } else if (richPath.name == Province.Kalimantan_Barat.toString()) {
                    showAlertDialog(richPath.name, "Balau, Bidayuh, Bukat, Buratmoto, Darai, Desa, Jangkang, Jelai, Iban, Lara, Limbai, Maloh,Manyuke, Mayau, Mentebak, Menyangka, Menyanya, Merau, Muluk Muduh, Muara, Mualang, Ngabang, Ngalampa, Ngamukit, Nganayatin, Panu, Pangkedang,Pompang, Pontianak, Punti, Randuk, Ribun, Sambas, Sanggau, Sani, Seberuang, Sekajang, Selayang, Senangkan, Senunang, Sisang, Sintang, Suhaid, Sungkung, Suruh, Taman, Tebuas, Tingui.")
                } else if (richPath.name == Province.Kalimantan_Tengah.toString()) {
                    showAlertDialog(richPath.name, "Aoeheng, Bakumpai, Bangka, Bawo, Empran, Gaat, Lawangan, Maanyan, Ngaju, Paku, Siang, Tamua.")
                } else if (richPath.name == Province.Kalimantan_Selatan.toString()) {
                    showAlertDialog(richPath.name, "Bajau, Bakumpai, Balangan, Bangka, Bukit, Pagatan, Pitap.")
                } else if (richPath.name == Province.Kalimantan_Timur.toString()) {
                    showAlertDialog(richPath.name, "Bajau, Banjar, Barau, Basap, Benuak, Berau, Berusu, Bulungan, Dayak, Long Gelat, Long Paka, Modang, Pasir, Penihing, Punan, Tidung, Timai, Tou, Tukung, Tunjung.")
                } else if (richPath.name == Province.Kalimatan_Utara.toString()) {
                    showAlertDialog(richPath.name, "Suku Dayak  (Lun Bawang/Lun dayeh. Kenyah, Murut), Banjar, Bulungan, Tidung, Kutai.")
                } else if (richPath.name == Province.Sulawesi_Selatan.toString()) {
                    showAlertDialog(richPath.name, "Ammatowa, Bajau, Benggalau, Bentong, Bonerate, Bugis,  Butong, Dungu, Galumpang, Kaladeng, Kalaotoa, Kajang, Luwu, Makassar, Mandar, Massenrengkulu, Selayar, Toala, Toraja, Towala-Wala.")
                } else if (richPath.name == Province.Sulawesi_Tenggara.toString()) {
                    showAlertDialog(richPath.name, "Aserawanua, Bajau, Buton, Tolaki, Mekongga, Wolio, Muna, Moronene, Kabaena, Wawonii, Bajau, Bugis, Wakatobi, Wawoni.")
                } else if (richPath.name == Province.Sulawesi_Barat.toString()) {
                    showAlertDialog(richPath.name, "Baras, Mandar, Dakka. Pattae, Pannei, Pattinjo, Bunggu, Da’a.")
                } else if (richPath.name == Province.Sulawesi_Tengah.toString()) {
                    showAlertDialog(richPath.name, "Balaesang, Balantak, Bancea, Banggai, Besoa, Boano, Bungku, Buoi,Buyu, Dale-dale, Dampelas, Dolo, Dondo, Kdamabuku, Kahummahon, Kaili, Kalae,Lauje, Loinang, Lo’on, Lore, Mori, Napu, Pamona, Pipikoro, Saluan, Napa-Bada, Tomimi, Toli-Toli, Saluan, Banggai, Tajio, Tolako, Toli-Toli, Tomia, Tomini, Wana.")
                } else if (richPath.name == Province.Gorontalo.toString()) {
                    showAlertDialog(richPath.name, "Bolaang Uki,Kaidipiang, Hulontalo, Polahi.")
                } else if (richPath.name == Province.Sulawesi_Utara.toString()) {
                    showAlertDialog(richPath.name, "Aatinggola, Bingi, Bintauna, Bolaang Itang, Gorontalo,  Mekongga, Minahasa, Moronene, Mongondow, Ponosakan, Ratahan, Sangir, Talaud, Tombulu, Tonswang, Tonsea, Tontenboan, Toulour.")
                } else if (richPath.name == Province.Maluku.toString()) {
                    showAlertDialog(richPath.name, "Alune, Amahai, Ambelau, Banda,Boano, Buru, Damar, Kadai, Erai, Laloda, Leti, Lumoli, Moa , Nuaulu, Pelauw, Rana, Seram, Tanimbar, Wemale. ")
                } else if (richPath.name == Province.Maluku_Utara.toString()) {
                    showAlertDialog(richPath.name, "Bacan, Buli, Gebe, Gane, Maba, Patani, Sawai, Weda, Galela, Tobelo, Loloda, Tobaru, Modole, Togutil, Pagu, Wailoa, Ibu, Sahu, Ternate, Tidore, Sula, Kadai, Mange, Siboyo, Makian, Sawai, Weda.")
                } else if (richPath.name == Province.Papua_Barat.toString()) {
                    showAlertDialog(richPath.name, "Amberbaken, Arguni, Atori, Ayamuru, Ayfat, Baburua, Baham, Buruwai, Busami, Irahutu, Iresim, Inanwatan, Lani, Mairasi, Mansim, Mare, Maeiyakh, Moire, Mooi, Ngalum, Palamul, Pisa, Sailolof, Uruwai, Wodani, Waropen, Wanggom, Wamesa, Yahrai, Yahadian.")
                } else if (richPath.name == Province.Papua.toString()) {
                    showAlertDialog(richPath.name, "Abau, Airoran. Amanab, Yapen, Aikwakai, Amungme, Arandai, Arfak, Asmat, Atam, Atogom, Awyi, Awyu, Banlol, Bauzi, Bedoanas, Berik, Bgu, Betch-mbup, Biak numor, Biksi, Bipim, Bisman, Boneraf, Borto, Brazza, Dani, Dem, Diemta, Dera, Emumu, Dou, Kaigir,Kaimo, Jinak, Joerat, Dubu, Eipomek, Ekagi, Emari, Fataluku, Fayu, Foau, Gressi, Hmanggona, Isirawa, Iwur, Jair, Janggu, Maden, Maniwa, Marind Anim, Mimika, Mey Brat, Muyu, Murop, Mosena, Moni, Mombun, Nimboran, Ngalik, Nduga, Samarokena, Sentani, Tabati, Tehid, Timorini, Waipu, Waipam, Waris, Wano, Yali, Yapen, Yey, Yaqay. ")
                }
            }
        })

        mapVideo = findViewById<View>(R.id.mapVideo) as VideoView
        val uri: Uri =
            Uri.parse("android.resource://" + packageName + "/" + R.raw.video)
        mapVideo.setVideoURI(uri)

        mapVideo.setOnPreparedListener({ mp -> mp.setLooping(true) })
//        var mapIndo = findViewById<View>(R.id.mapIndo) as RichPathView
//        mapIndo.setOnPathClickListener(OnPathClickListener { richPath ->
//            if (richPath.name == "path_name") {
//                //TODO do an action when a specific path is clicked.
//            }
//        })
    }

    fun showAlertDialog(title: String, message: String) {
        // Use the Builder class for convenient dialog construction
        val builder = AlertDialog.Builder(this)
        var newTitle: String = title.replace("_", " ")
        builder.setTitle("Suku " + newTitle)
                .setMessage(message)
                .setPositiveButton("OK", { dialog, id ->
                           dialog.dismiss()
                })
        // Create the AlertDialog object and return it
        builder.create()
        builder.show()
    }

    override
    public fun onResume() {
        mapVideo.start()
        mapVideo.requestFocus()
        super.onResume()
    }
}