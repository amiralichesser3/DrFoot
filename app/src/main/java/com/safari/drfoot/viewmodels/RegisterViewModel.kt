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
                    Section(3, "Ischemic ", true, null, null),
                    Section(4, "Neuroischaemic", true, null, null),
                    // for healthy foot
                    Section(5, "Patient History", false, null, 1),
                    Section(6, "Laboratory Test", false, null, 1),
                    Section(7, "Overall Physical Exam", false, null, 1),
                    Section(8, "Foot Examination", false, null, 1)
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
                    Answer(1, "Diagnosis",  false, 5,"No Ulcers", null),
                    Answer(2, "Diagnosis",  true, 5,"At risk of DFU", null),
                    Answer(3, "Diagnosis",  false, 5,"Neuropathic DFU", null),
                    Answer(1, "Diagnosis",  false, 5,"Ischemic DFU", null),
                    Answer(2, "Diagnosis",  false, 5,"Neuroischemic DFU", null),
                    Answer(3, "Diagnosis",  false, 5,"Infectious DFU", null),
                    Answer(3, "Diagnosis",  false, 5,"Charcot foot", null),

                    Answer(1, "Management",  true, 5,"Self-care education for the patient", null),
                    Answer(2, "Management",  true, 5,"Periodic examinations", null),
                    Answer(3, "Management",  false, 5,"Debridement", null),
                    Answer(4, "Management",  false, 5,"Dressing", null),
                    Answer(5, "Management",  false, 5,"Antibiotics", null),
                    Answer(2, "Management",  false, 5,"Revascularization", null),
                    Answer(2, "Management",  true, 5,"Offloading", null),
                    Answer(2, "Management",  false, 5,"Refer to other specialists", null),
                    Answer(2, "Management",  false, 5,"Rehabilitation", null)
                )
                answerRepo.save(answers)
            }
        }
    }
}