package ru.beryukhov.mpp.presenter

//import com.soywiz.klock.DateTime
//import com.soywiz.klock.DateTimeSpan
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import ru.beryukhov.mpp.domain.DateModel
import ru.beryukhov.mpp.domain.DateTime
import ru.beryukhov.mpp.domain.TimeSheetInteractor
import ru.beryukhov.mpp.domain.TimeSheetInteractorImpl
import ru.beryukhov.mpp.domain.TimeSheetRepository
import ru.beryukhov.mpp.view.TimeSheetView
import kotlin.coroutines.CoroutineContext

internal /*expect*/ val commonCoroutineContext: CoroutineContext =Dispatchers.Default

/**
 * Created by Andrey Beryukhov
 */
class TimeSheetPresenter(val timeSheetView: TimeSheetView, private val timeSheetRepository: TimeSheetRepository) {
    val timeSheetInteractor: TimeSheetInteractor

    init {
        timeSheetInteractor = TimeSheetInteractorImpl(timeSheetRepository)
    }

    fun onCreateView() {
        timeSheetView.showProgress()
        GlobalScope.launch(Dispatchers.Default) {
            val dates = async { timeSheetInteractor.getDatesList(getStartDate()) }

            timeSheetView.addAll(dates.await())
            timeSheetView.hideProgress()

        }
    }

    fun onFixStart() {
        timeSheetView.showProgress()
        GlobalScope.launch(commonCoroutineContext) {
            val dates = async {
                timeSheetInteractor.addStartTime(DateTime.now())
                timeSheetInteractor.getDatesList(getStartDate())
            }

            timeSheetView.addAll(dates.await())
            timeSheetView.hideProgress()

        }
    }

    fun onFixEnd() {
        timeSheetView.showProgress()
        GlobalScope.launch(commonCoroutineContext) {
            val dates = async {
                timeSheetInteractor.addEndTime(DateTime.now())
                timeSheetInteractor.getDatesList(getStartDate())
            }

            timeSheetView.addAll(dates.await())
            timeSheetView.hideProgress()

        }
    }

    fun onItemClick(item: DateModel) { /*todo*/
    }

    private fun getStartDate() = DateTime.now() //- DateTimeSpan(days = 7)

}