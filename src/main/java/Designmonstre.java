public class Designmonstre {
    /*3.1
    State designmønster
    Problem: et problem for designmønsteret state kan være et program som implementerer en hårføner med en knapp. Når knappen trykkes, øker styrken
    til hårføneren. Hårføneren slås av når man trykker på knappen etter at den høyeste styrken er på. Av den grunn avhenger oppførslen til objektet av tilstanden den er i

    Strategy designmønster
    Problem: et problem kan være hvordan en passasjer kommer seg til flyplassen med gitte transportmidler; buss, tog, bil, drosje osv. Poenget er å bruke den strategien
    som er mest hensiktsmessig for personen selv for å komme seg fra A til B.




     */
    //gitt hårføneren tilstandene høy, lav, av.
    interface HårfønerStatus {
        HårfønerStatus endreStyrke();

        String getStatus();
    }

    class Hårføner_AV implements HårfønerStatus {
        @Override
        public HårfønerStatus endreStyrke() {
            return new Hårføner_lav();
        }

        @Override
        public String getStatus() {
            return "AV";
        }
    }

    class Hårføner_lav implements HårfønerStatus {
        @Override
        public HårfønerStatus endreStyrke() {
            return new Hårføner_Høy();
        }

        @Override
        public String getStatus() {
            return "LAV";
        }
    }

    class Hårføner_Høy implements HårfønerStatus {
        @Override
        public HårfønerStatus endreStyrke() {
            return new Hårføner_AV();
        }

        @Override
        public String getStatus() {
            return "hØY";
        }
    }

    class Hårføner {
        private HårfønerStatus status = new Hårføner_AV();

        public void trykk() {
            status = status.endreStyrke();
        }

        public void printStatus() {
            System.out.println(status.getStatus());

    Hårføner Hårføner = new Hårføner();
    Hårføner.trykk();
    Hårføner.printStatus();
    Hårføner.trykk();
    Hårføner.printStatus();
    Hårføner.trykk();
    Hårføner.printStatus();

    }
}}