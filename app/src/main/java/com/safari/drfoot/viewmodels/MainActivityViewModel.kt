package com.safari.drfoot.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.os.AsyncTask
import com.safari.drfoot.R
import com.safari.drfoot.entities.*
import com.safari.drfoot.repositories.*
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(private val gameLevelRepo: GameLevelRepository,
                                                private val personRepo: PersonRepository,
                                                private val personGameLevelRepo: PersonGameLevelRepository,
                                                private val demographicRepository: DemographicRepository,
                                                private val historyRepository: HistoryRepository,
                                                private val investigationRepository: InvestigationRepository,
                                                private val examinationRepository: ExaminationRepository) : ViewModel()  {
    lateinit var gameLevels: LiveData<List<GameLevel>>

    fun init() {
        seedDatabase()
        gameLevels = gameLevelRepo.load()
    }

    private fun seedDatabase() {
        AsyncTask.execute {
            if (!gameLevelRepo.exists()) {
                val gameLevels = arrayListOf(GameLevel(1, "Prevention", false, "https://www.uottawa.ca/health/sites/www.uottawa.ca.health/files/icons/Icon---Chiropody---V2.png"),
                    GameLevel(2, "Examination", true, null),
                    GameLevel(3, "Detection", true, null),
                    GameLevel(4, "Healing", true, null),
                    GameLevel(5, "Amputated", true, null))
                gameLevelRepo.save(gameLevels)
            }

            if (!personRepo.exists()) {
                val people = arrayListOf(Person(1, "Muhammad", null, R.drawable.oldman,
                    "Demographic Info 1", "History Info 1", "Main Problem 1",
                    "Initial Examination Result 1", "Lab Results 1", false),
                    Person(2, "Zahra", null, R.drawable.oldwoman,
                        "Demographic Info 2", "History Info 2", "Main Problem 2",
                        "Initial Examination Result 2", "Lab Results 2", true),
                    Person(3, "Ali", null, R.drawable.youngman,
                        "Demographic Info 3", "History Info 3", "Main Problem 3",
                        "Initial Examination Result 3", "Lab Results 3", true),
                    Person(4, "Maryam", null, R.drawable.youngwoman,
                        "Demographic Info 4", "History Info 4", "Main Problem 4",
                        "Initial Examination Result 4", "Lab Results 4", true),
                    Person(5, "Sam", null, R.drawable.kid,
                        "Demographic Info 5", "History Info 5", "Main Problem 5",
                        "Initial Examination Result 5", "Lab Results 5", true))
                personRepo.save(people)
            }

            if (!personGameLevelRepo.exists()) {
                val personGameLevels = arrayListOf(PersonGameLevel(1, 1),
                    PersonGameLevel(2, 1), PersonGameLevel(3, 1),
                    PersonGameLevel(4, 1), PersonGameLevel(5, 1))
                personGameLevelRepo.save(personGameLevels)
            }

            if (!demographicRepository.exists()) {
                val demographics = arrayListOf(Demographic(1, 1,
                    "بیمار آقای 50 ساله، متاهل، راننده تاکسی، صاحب دو فرزند دانشجو است. در خانواده چهارنفری زندگی می کند. ملک شخصی دارد و درآمد ماهیانه وی برابر با هزینه کرد و گاهی کمتر است.",
                    "از  4 سال پیش مبتلا به دیابت نوع 2 شده است. سابقه فشار خون بالا  نیز از حدود 1 سال پیش دارد و تحت نظر پزشک دارو مصرف می کند. \n" +
                            "چشمان بیمار ضعیف است و اگرچه فاصله دور را به خوبی می بیند ولی در مشاهده فواصل نزدیک حتی با عینک مشکل دارد.",
                    "    • قرص مت فورمین500  میلی گرم: سه بار در روز\n" +
                            "    • قرص گلی کلازید میلی گرم 5: یک بار در روز (ظهر)\n" +
                            "    • قرص آملودیپین 5 میلی گرم: یک بار در روز (ظهر)\n" +
                            "    • قرص آ اس آ 80 میلی گرم: یک بار در روز",
                    "بیمار تا کنون هیچ شواهدی دال بر بروز عوارض دیابت نداشته است. سابقه زخم پای دیابتی نیز ندارد.\n" +
                            "    • بیماریهای قلبی و عروقی \uF078\n" +
                            "    • نوروپاتی \uF078\n" +
                            "    • نفروپاتی \uF078\n" +
                            "    • رتینوپاتی \uF078\n" +
                            "    • زخم پا \uF078",
                    R.drawable.labresult1,
                    "قد: 165 سانتی متر      وزن: 80 کیلوگرم       دور شکم: 120 سانتی متر     دور کمر: ---      دور باسن:--- ",
                    "بیمار عنوان می کند که پوست هر دو پایش سالم و بدون زخم است و هیچ گونه ادم و تغییر رنگی در پاهایش مشهود نیست. شکایتی دال بر درد و اختلال حس در پاها مطرح نیست. ",
                    R.drawable.presentingcomplain1))
                demographicRepository.save(demographics)
            }

            if (!historyRepository.exists()) {
                val histories = arrayListOf(History(1, 1,
                    "بیمار سابقه زخم پا، آمپوتاسیون محدود یا وسیع ندارد. سابقه آنژیوپلاستی محیطی نیز گزارش نشده است.",
                    "بیمار از  4 سال پیش مبتلا به دیابت نوع 2 شده است. سابقه فشار خون بالا  نیز از حدود 1 سال پیش دارد و تحت نظر پزشک دارو مصرف می کند.",
                    R.drawable.diabeteshistory1,
                    "    • قرص مت فورمین500  میلی گرم: سه بار در روز\n" +
                            "    • قرص گلی کلازید میلی گرم 5: یک بار در روز (ظهر)\n" +
                            "    • قرص آملودیپین 5 میلی گرم: یک بار در روز (ظهر)\n" +
                            "    • قرص آ اس آ 80 میلی گرم: یک بار در روز\n" +
                            "سوابق حساسیت دارویی مطرح نیست.",
                    "بیمار سابقه بیماری حاد یا مزمن دیگری ندارد. سابقه تصادف و بستری در بیمارستان نیز ندارد.",
                    "سابقه دیابت نوع 2 در پدر، عموی بزرگ، دو عمه و برادر بزرگتر بیمار وجود دارد. به علاوه سابقه سکته قلبی در خانواده مادری وجود دارد. علت فوت پدر و عموی بیمار نیز سکته قلبی در خواب گزارش شده است.",
                    "سابقه دیابت نوع 2 در پدر، عموی بزرگ، دو عمه و برادر بزرگتر بیمار وجود دارد. به علاوه سابقه سکته قلبی در خانواده مادری وجود دارد. علت فوت پدر و عموی بیمار نیز سکته قلبی در خواب گزارش شده است."
                    ))
                historyRepository.save(histories)
            }

            if (!investigationRepository.exists()) {
                val investigations = arrayListOf(Investigation(1, 1, R.drawable.investigation1))
                investigationRepository.save(investigations)
            }

            if (!examinationRepository.exists()) {
                val examinations = arrayListOf(Examination(1, 1,
                    "در مشاهده پوست هر دوپای بیمار سالم و به رنگ طبیعی است.  بستر و رنگ ناخن ها طبیعی بوده  و هیچگونه ادمی در پاها مشاهده نمی شود. هیچگونه دفورمیتی و محدودیت حرکتی در پاها و انگشتان رویت نمی شود.",
                    R.drawable.inspection1,
                    "در معاینه نورولوژیکی، شواهدی دال بر نوروپاتی حرکتی، نوروپاتی حسی و نوروپاتی اتونوم وجود ندارد.",
                    "در معاینه نورولوژیکی، شواهدی دال بر نوروپاتی حرکتی، نوروپاتی حسی و نوروپاتی اتونوم وجود ندارد.",
                    "    • فشار خون سیستولیک: 140 میلی گرم جیوه\n" +
                            "    • فشار خون دیاستولیک: 85  میلی گرم جیوه\n" +
                            "    • تعداد ضربان قلب: 75 ضربه در دقیقه\n" +
                            " در بررسی وضعیت تنفسی:\n" +
                            "-------\n" +
                            "در معاینه شکمی:\n" +
                            "------\n" +
                            "در معاینه چشم ها:\n" +
                            "    • حدت بینایی چشم راست:     \n" +
                            "    • حدت بینایی چشم چپ:  "))
                examinationRepository.save(examinations)
            }

        }
    }
}