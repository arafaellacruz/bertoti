module com.rafaellacruz.lolbuildbuddy {

    requires telegrambots.meta;
    requires telegrambots.client;
    requires org.slf4j;
    requires ollama4j;
    requires telegrambots.longpolling;
    requires java.net.http;
    requires org.apache.commons.lang3;
    requires org.apache.commons.csv;

    opens com.rafaellacruz.lolbuildbuddy to javafx.fxml;
    exports com.rafaellacruz.lolbuildbuddy;
}
