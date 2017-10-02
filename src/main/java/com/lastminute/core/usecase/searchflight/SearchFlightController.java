package com.lastminute.core.usecase.searchflight;


import com.lastminute.core.controller.Controller;

/**
 * Implementation of a Controller for flight search
 */
public class SearchFlightController implements Controller<SearchFlightRequest,SearchFlightRequestException> {

    SearchFlightsOutputBoundary presenter;
    SearchFlightsInputBoundary usecase;
    SearchFlightView view;

    public SearchFlightController(SearchFlightsOutputBoundary presenter, SearchFlightsInputBoundary usecase, SearchFlightView view) {
        this.presenter = presenter;
        this.usecase = usecase;
        this.view = view;
    }

    public SearchFlightsOutputBoundary getPresenter() {
        return presenter;
    }

    public SearchFlightsInputBoundary getUsecase() {
        return usecase;
    }

    public SearchFlightView getView() {
        return view;
    }


    /**
     * Validates user input,
     * search flights,
     * applies business rules to results,
     * prepares output and
     * tells the view to represent it
     * @param request
     * @throws SearchFlightRequestException if incorrect input
     */
    @Override
    public void handleRequest(SearchFlightRequest request) throws SearchFlightRequestException {
        request.validate();
        usecase.searchFlights(request,presenter);
        view.generateView(presenter.getViewModel());

    }
}
