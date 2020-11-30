package com.safari.drfoot.viewmodels
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.os.AsyncTask
import com.safari.drfoot.R
import com.safari.drfoot.entities.*
import com.safari.drfoot.repositories.*
import javax.inject.Inject

class RegisterViewModel @Inject constructor(private val meRepo: MeRepository,
                                            private val personRepo: PersonRepository,
                                            private val sectionRepo: SectionRepository,
                                            private val subSectionRepo: SubSectionRepository,
                                            private val answerRepo: AnswerRepository) : ViewModel()  {
    lateinit var me: LiveData<Me>

    fun init() {
        me = meRepo.load()
        if (!exists()) {
            save(Me(1, null, null, null, null, false))
        }
    }

    fun exists(): Boolean {
        return meRepo.exists();
    }

    fun save(me: Me?) {
        if (me == null) return
        AsyncTask.execute {
            meRepo.save(me)
        }
    }

    fun seedDatabase() {
        AsyncTask.execute {
            if (!sectionRepo.exists()) {
                val sections = arrayListOf(
                    Section(1, "Healthy Foot", false,
                        "https://www.enpcn.com/wp-content/uploads/2017/08/Footcare-180x0-c-default.png", null),
                    Section(2, "Neuropathic", true,
                        "https://thumbs.dreamstime.com/b/diabetic-foot-line-icon-diabetic-foot-line-icon-editable-stroke-pixel-perfect-125899816.jpg",
                        null),
                    Section(3, "Ischemic ", true, "https://image.shutterstock.com/image-vector/diabetic-foot-monochrome-icon-260nw-756262675.jpg", null),
                    Section(4, "Neuroischaemic", true, "https://thumbs.dreamstime.com/b/diabetic-foot-line-icon-diabetic-foot-line-icon-editable-stroke-pixel-perfect-125899816.jpg", null),
                    // for healthy foot
                    Section(5, "Patient History", false, "data:image/png;base64,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",
                        1),
                    Section(6, "Laboratory Test", false, "data:image/png;base64,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",
                        1),
                    Section(7, "Overall Physical Exam", false, "https://static.thenounproject.com/png/301421-200.png", 1),
                    Section(8, "Foot Examination", false, "data:image/jpeg;base64,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",
                        1)
                )
                sectionRepo.save(sections)
            }

            if (!subSectionRepo.exists()) {
                val subsections = arrayListOf(
                    SubSection(1, "Demographic Information", 1, false,
                        "https://icon-library.com/images/demographics-icon/demographics-icon-5.jpg", 5,
                        "Tim is a 50-year-old married man, a taxi driver, who has two student children. He lives in a family of four." +
                                " He owns private property and his monthly income is equal to his expenses and sometimes less.",
                        null, null, null, null, null),
                    SubSection(2, "Chief Complaint", 1, false,
                        null, 5,    "Tim suffers from unpleasant tingling and numbness." +
                                " He states that “my feet feel as if they do not belong to me”. Complains of tingling and tingling in the legs at night.",
                        null,
                        "Diabetic foot lesions frequently result from a patient simultaneously having two or more risk factors," +
                                " with diabetic peripheral neuropathy playing a central role. his neuropathy leads to an insensitive and sometimes deformed foot," +
                                " often causing an abnormal walking pattern.",
                        null, null, null),
                    SubSection(3, "Past Foot History", 1, false,
                        "https://www.uottawa.ca/health/sites/www.uottawa.ca.health/files/icons/Icon---Chiropody---V2.png", 5,
                       "Tim says that both feet are healthy without any wounds or edema right now." +
                               " However he suffered from a hyperkeratotic lesion under first metatarsal head of the right foot which healed successfully 2 years ago.",
                        null,
                        "Male gender, previous foot ulceration or amputation, foot deformities, calluses," +
                                " Charcot arthropathy and high plantar pressures would be associated with an increased risk for foot ulceration.",
                        null, null, null),
                    SubSection(4, "Diabetic History", 1, false,
                        null, 5,     "Tim has suffered from type 2 Diabetes Mellitus since 14 years ago." +
                                " He has taken oral diabetes medications including Metformin 500 (TDS) and Gliclazide (Daily)." +
                                " He has also suffered from retinopathy from 3 years ago who had laser therapy.",  null,
                        "The duration of diabetes, poor glycemic control," +
                                " diabetic retinopathy or nephropathy would be considered as diabetes-related risk factors which influence the risk of foot ulcers.",
                        null, null, null),
                    SubSection(5, "Past Medical History", 1, false,
                        null, 5,     "Tim has been suffering from hypertension for 8 years.",
                        null, null, null, null, null),
                   SubSection(6, "Psychosocial History", 1, false,
                       null, 5,  "Tim has smoked 4 cigarettes per day since 15 years ago." +
                               " He has not reported any history psychiatric illness, drug or alcohol dependency. He also lives in a family.",
                    null,null, ""  , null, null)
                )
                subSectionRepo.save(subsections)
            }

            if (!personRepo.exists()) {
                val people = arrayListOf(Person(1, "Tim", null, R.drawable.oldman,
                    "Demographic Info 1", "History Info 1", "Main Problem 1",
                    "Initial Examination Result 1", "Lab Results 1", false),
                    Person(2, "Alyx", null, R.drawable.oldwoman,
                        "Demographic Info 2", "History Info 2", "Main Problem 2",
                        "Initial Examination Result 2", "Lab Results 2", true),
                    Person(3, "Jeff", null, R.drawable.youngman,
                        "Demographic Info 3", "History Info 3", "Main Problem 3",
                        "Initial Examination Result 3", "Lab Results 3", true),
                    Person(4, "Mina", null, R.drawable.youngwoman,
                        "Demographic Info 4", "History Info 4", "Main Problem 4",
                        "Initial Examination Result 4", "Lab Results 4", true),
                    Person(5, "Sam", null, R.drawable.kid,
                        "Demographic Info 5", "History Info 5", "Main Problem 5",
                        "Initial Examination Result 5", "Lab Results 5", true))
                personRepo.save(people)
            }

            if (!answerRepo.exists()) {
                var answers = arrayListOf(
                    Answer(1, 1, "Diagnosis",  false, 5,"No Ulcers", null),
                    Answer(2, 1, "Diagnosis",  true, 5,"At risk of DFU", null),
                    Answer(3, 1, "Diagnosis",  false, 5,"Neuropathic DFU", null),
                    Answer(4, 1, "Diagnosis",  false, 5,"Ischemic DFU", null),
                    Answer(5, 1, "Diagnosis",  false, 5,"Neuroischemic DFU", null),
                    Answer(6, 1, "Diagnosis",  false, 5,"Infectious DFU", null),
                    Answer(7, 1, "Diagnosis",  false, 5,"Charcot foot", null),

                    Answer(8, 1, "Management",  true, 5,"Self-care education for the patient", null),
                    Answer(9, 1, "Management",  true, 5,"Periodic examinations", null),
                    Answer(10, 1, "Management",  false, 5,"Debridement", null),
                    Answer(11, 1, "Management",  false, 5,"Dressing", null),
                    Answer(12, 1, "Management",  false, 5,"Antibiotics", null),
                    Answer(13, 1, "Management",  false, 5,"Revascularization", null),
                    Answer(14, 1, "Management",  true, 5,"Offloading", null),
                    Answer(15, 1, "Management",  false, 5,"Refer to other specialists", null),
                    Answer(16, 1, "Management",  false, 5,"Rehabilitation", null)
                )
                answerRepo.save(answers)
            }
        }
    }
}