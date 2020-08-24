package com.example.currencytest.MainModule

class MainConfigurator: MainContract.Configurator{

    override fun configurate(view: MainActivity) {
        val presenter = MainPresenter(view)
        val router = MainRouter(view)
        val interactor = MainInteractor(view)

        presenter.router = router
        view.presenter = presenter
        presenter.interactor = interactor
    }

}