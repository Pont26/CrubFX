/*module DemoCrud {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires javafx.base;
	
	opens application to javafx.graphics, javafx.fxml;
}
*/
module DemoCrud {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.base;
	requires javafx.graphics;

    // Open packages for reflective access by JavaFX
    opens application to javafx.graphics, javafx.fxml;
    opens com.demoncrud.model to javafx.base;
    opens controller to javafx.fxml;
// Allow reflective access for JavaFX

    // Export other packages if needed
    exports application;
    exports com.demoncrud.model;
    exports com.demoncrud.dao;
}
