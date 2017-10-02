package com.lastminute.preparedOutputs;

public class SearchViewOutputs {

    public static String get1Passenger31DaysAMStoFRA() {
        return String.join("\n",
                "* 1 passenger, 31 days to the departure date, flying AMS -> FRA",
                "",
                "  flights:",
                "    * TK2372, 157.6 €",
                "    * TK2659, 198.4 €",
                "    * LH5909, 90.4 €"
                );
    }


    public static String get3Passenger15daysLHRtoIST() {
        return String.join("\n",
                "* 3 passengers, 15 days to the departure date, flying LHR -> IST",
                "",
                "  flights:",
                "    * TK8891, 900 € (3 * (120% of 250))",
                "    * LH1085, 532.8 € (3 * (120% of 148))"
        );
    }


    public static String get2Passenger2DaysBCNtoMAD() {
        return String.join("\n",
                "* 2 passengers, 2 days to the departure date, flying BCN -> MAD",
                "",
                "  flights:",
                "    * IB2171, 777 € (2 * (150% of 259))",
                "    * LH5496, 879 € (2 * (150% of 293))"
        );
    }


    public static String get2Passenger2DaysCDGtoFRA() {
        return String.join("\n",
                "* CDG -> FRA",
                "",
                "  no flights available"
        );
    }
}
