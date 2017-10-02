package com.lastminute.core.usecase.searchflight;

import com.lastminute.core.entity.FlightResult;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import static org.junit.Assert.*;

public class SearchFlightsPresenterTest {

    SearchFlightsPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new SearchFlightsPresenter();
    }
    /*
    * 1 passenger, 31 days to the departure date, flying AMS -> FRA

      flights:

        * TK2372, 157.6 €
        * TK2659, 198.4 €
        * LH5909, 90.4 €
     */

    @Test
    public void viewModelOf1Passenger31days() {
        //Given
        List<FlightResult> results = new ArrayList();
        results.add(getResult("LOL",2,3,1,"ETH"));
        SearchFlightRequest request = new SearchFlightRequest("HERE","THERE",31,1);
        SearchFlightResponse response = new SearchFlightResponse(results,request);

        //When
        presenter.present(response);

        //Then
        SearchFlightViewModel model = presenter.getViewModel();
        String desired = String.join("\n",
                "* 1 passenger, 31 days to the departure date, flying HERE -> THERE",
                "",
                "  flights:",
                "    * LOL, 3 ETH"
                );

        assertEquals(model.getPrompt(),desired);

    }

    /*
        * 3 passengers, 15 days to the departure date, flying LHR -> IST

            flights:

                * TK8891, 900 € (3 * (120% of 250))
                * LH1085, 532.8 € (3 * (120% of 148))
    * */

    @Test
    public void viewModelOf3Passenger15days() {
        //Given
        List<FlightResult> results = new ArrayList();
        results.add(getResult("TK8891",250,900,1.2,"€"));
        results.add(getResult("LH1085",148,532.8,1.2,"€"));

        SearchFlightRequest request = new SearchFlightRequest("LHR","IST",15,3);
        SearchFlightResponse response = new SearchFlightResponse(results,request);

        //When
        presenter.present(response);

        //Then
        SearchFlightViewModel model = presenter.getViewModel();
        String desired = String.join("\n",
                "* 3 passengers, 15 days to the departure date, flying LHR -> IST",
                "",
                "  flights:",
                "    * TK8891, 900 € (3 * (120% of 250))",
                "    * LH1085, 532.8 € (3 * (120% of 148))"
        );

        assertEquals(model.getPrompt(),desired);

    }

    /*
    * 2 passengers, 2 days to the departure date, flying BCN -> MAD

        flights:

            * IB2171, 777 € (2 * (150% of 259))
            * LH5496, 879 € (2 * (150% of 293))

     */

    @Test
    public void viewModelOf2Passenger2days() {
        //Given
        List<FlightResult> results = new ArrayList();
        results.add(getResult("IB2171",259,777,1.5,"€"));
        results.add(getResult("LH5496",293,879,1.5,"€"));

        SearchFlightRequest request = new SearchFlightRequest("BCN","MAD",2,2);
        SearchFlightResponse response = new SearchFlightResponse(results,request);

        //When
        presenter.present(response);

        //Then
        SearchFlightViewModel model = presenter.getViewModel();
        String desired = String.join("\n",
                "* 2 passengers, 2 days to the departure date, flying BCN -> MAD",
                "",
                "  flights:",
                "    * IB2171, 777 € (2 * (150% of 259))",
                "    * LH5496, 879 € (2 * (150% of 293))"
        );

        assertEquals(model.getPrompt(),desired);

    }



    /*

    * CDG -> FRA

        no flights available

     */
    @Test
    public void noFlightsAvailable() {
        //Given
        List<FlightResult> results = new ArrayList();

        SearchFlightRequest request = new SearchFlightRequest("CDG","FRA",2,2);
        SearchFlightResponse response = new SearchFlightResponse(results,request);

        //When
        presenter.present(response);

        //Then
        SearchFlightViewModel model = presenter.getViewModel();
        String desired = String.join("\n",
                "* CDG -> FRA",
                "",
                "  no flights available"
        );

        assertEquals(model.getPrompt(),desired);

    }


    private static FlightResult getResult(String code, double original,double finalPrice, double modif, String currency) {
        return new FlightResult.Builder(code,original)
                .passengerModifierApplied(original)
                .currency(currency)
                .dayModifierApplied(modif,finalPrice)
                .build();
    }
}