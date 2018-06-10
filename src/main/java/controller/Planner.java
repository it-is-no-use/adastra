package controller;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import entity.Drivers;
import entity.Materials;
import entity.Objects;
import entity.Trucks;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import repository.DriversRepository;
import repository.JobRepository;
import repository.MaterialsRepository;
import repository.ObjectsRepository;
import repository.TrucksRepository;
import repository.UsersRepository;

public class Planner {

	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	UsersRepository usersRepository = context.getBean(UsersRepository.class);
	ObjectsRepository objectsRepository = context.getBean(ObjectsRepository.class);
	DriversRepository driversRepository = context.getBean(DriversRepository.class);
	TrucksRepository trucksRepository = context.getBean(TrucksRepository.class);
	MaterialsRepository materialsRepository = context.getBean(MaterialsRepository.class);
	JobRepository jobRepository = context.getBean(JobRepository.class);
	
	//Вкладка "Действия со справочниками"
	
	//Справочник "Объекты"
	
	@FXML
	private TableView<Objects> ob_table;
	
	@FXML
	private TableColumn<Objects,Number> ob_id;
	
	@FXML
	private TableColumn<Objects,String> ob_name;
	
	@FXML
	private TextField ob_txt1;
	
	@FXML
	private TextField ob_txt2;
	
	@FXML
	private void ob_add() {
		
		Objects object = new Objects();
		
		object.setOb_id(Integer.valueOf(ob_txt1.getText()));
		object.setOb_name(ob_txt2.getText());
		objectsRepository.save(object);
		ob_table.setItems(FXCollections.observableArrayList(objectsRepository.findAll()));
		ob_txt1.clear();
		ob_txt2.clear();
	}
	
	@FXML
	private void ob_edit() {
		
		ob_txt1.setText(ob_table.getSelectionModel().getSelectedItem().getOb_id().toString());
		ob_txt2.setText(ob_table.getSelectionModel().getSelectedItem().getOb_name());
	}
	
	@FXML
	private void ob_remove() {
		
		objectsRepository.delete(ob_table.getSelectionModel().getSelectedItem().getOb_id());
		ob_table.setItems(FXCollections.observableArrayList(objectsRepository.findAll()));
	}
	
	//Справочник "Машины"
	
	@FXML
	private TableView<Trucks> truck_table;
	
	@FXML
	private TableColumn<Trucks,Number> truck_id;
	
	@FXML
	private TableColumn<Trucks,String> truck_name;
	
	@FXML
	private TableColumn<Trucks,String> gov_number;
		
	@FXML
	private TextField truck_txt1;
	
	@FXML
	private TextField truck_txt2;
	
	@FXML
	private TextField truck_txt3;
	
	@FXML
	private void truck_add() {
		
		Trucks truck = new Trucks();
		
		truck.setTruck_id(Integer.valueOf(truck_txt1.getText()));
		truck.setTruck_name(truck_txt2.getText());
		truck.setGov_number(truck_txt3.getText());
		trucksRepository.save(truck);
		truck_table.setItems(FXCollections.observableArrayList(trucksRepository.findAll()));
		truck_txt1.clear();
		truck_txt2.clear();
		truck_txt3.clear();
	}
	
	@FXML
	private void truck_edit() {
		
		truck_txt1.setText(truck_table.getSelectionModel().getSelectedItem().getTruck_id().toString());
		truck_txt2.setText(truck_table.getSelectionModel().getSelectedItem().getTruck_name());
		truck_txt3.setText(truck_table.getSelectionModel().getSelectedItem().getGov_number());
	}
	
	@FXML
	private void truck_remove() {
		
		trucksRepository.delete(truck_table.getSelectionModel().getSelectedItem().getTruck_id());
		truck_table.setItems(FXCollections.observableArrayList(trucksRepository.findAll()));
	}
	
	//Справочник "Водители"
	
	@FXML
	private TableView<Drivers> driver_table;
	
	@FXML
	private TableColumn<Drivers,Number> driver_id;
	
	@FXML
	private TableColumn<Drivers,String> driver_fullname;
	
	@FXML
	private TextField driver_txt1;
	
	@FXML
	private TextField driver_txt2;
	
	@FXML
	private void driver_add() {
		
		Drivers driver = new Drivers();
		
		driver.setDriver_id(Integer.valueOf(driver_txt1.getText()));
		driver.setDriver_fullname(driver_txt2.getText());
		driversRepository.save(driver);
		driver_table.setItems(FXCollections.observableArrayList(driversRepository.findAll()));
		driver_txt1.clear();
		driver_txt2.clear();
	}
	
	@FXML
	private void driver_edit() {
		
		driver_txt1.setText(driver_table.getSelectionModel().getSelectedItem().getDriver_id().toString());
		driver_txt2.setText(driver_table.getSelectionModel().getSelectedItem().getDriver_fullname());
	}
	
	@FXML
	private void driver_remove() {
		
		driversRepository.delete(driver_table.getSelectionModel().getSelectedItem().getDriver_id());
		driver_table.setItems(FXCollections.observableArrayList(driversRepository.findAll()));
	}
	
	//Справочник "Материалы"
	
	@FXML
	private TableView<Materials> mat_table;
	
	@FXML
	private TableColumn<Materials,Number> mat_id;
	
	@FXML
	private TableColumn<Materials,String> mat_name;
	
	@FXML
	private TextField mat_txt1;
	
	@FXML
	private TextField mat_txt2;
	
	@FXML
	private void mat_add() {
		
		Materials material = new Materials();
		
		material.setMat_id(Integer.valueOf(mat_txt1.getText()));
		material.setMat_name(mat_txt2.getText());
		materialsRepository.save(material);
		mat_table.setItems(FXCollections.observableArrayList(materialsRepository.findAll()));
		mat_txt1.clear();
		mat_txt2.clear();
	}
	
	@FXML
	private void mat_edit() {
		
		mat_txt1.setText(mat_table.getSelectionModel().getSelectedItem().getMat_id().toString());
		mat_txt2.setText(mat_table.getSelectionModel().getSelectedItem().getMat_name());
	}
	
	@FXML
	private void mat_remove() {
		
		materialsRepository.delete(mat_table.getSelectionModel().getSelectedItem().getMat_id());
		mat_table.setItems(FXCollections.observableArrayList(materialsRepository.findAll()));
	}
	
	@FXML
	public void initialize() {
	
		//Вкладка "Действия со справочниками"
		
		//Объекты
		
		ob_table.setItems(FXCollections.observableArrayList(objectsRepository.findAll()));
	
		ob_name.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Objects,String>, ObservableValue<String>>(){
			public ObservableValue<String> call (TableColumn.CellDataFeatures<Objects, String> param){
				return new SimpleStringProperty(param.getValue().getOb_name());
			}
		});
		
		ob_id.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Objects, Number>, ObservableValue<Number>>(){
			public ObservableValue<Number> call (TableColumn.CellDataFeatures<Objects, Number> param){
				return new SimpleIntegerProperty(param.getValue().getOb_id());
			}
		});
		
		//Машины
		
		truck_table.setItems(FXCollections.observableArrayList(trucksRepository.findAll()));
	
		truck_name.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Trucks,String>, ObservableValue<String>>(){
			public ObservableValue<String> call (TableColumn.CellDataFeatures<Trucks, String> param){
				return new SimpleStringProperty(param.getValue().getTruck_name());
			}
		});
		
		truck_id.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Trucks, Number>, ObservableValue<Number>>(){
			public ObservableValue<Number> call (TableColumn.CellDataFeatures<Trucks, Number> param){
				return new SimpleIntegerProperty(param.getValue().getTruck_id());
			}
		});
		
		gov_number.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Trucks,String>, ObservableValue<String>>(){
			public ObservableValue<String> call (TableColumn.CellDataFeatures<Trucks, String> param){
				return new SimpleStringProperty(param.getValue().getGov_number());
			}
		});
		
		//Водители
		
		driver_table.setItems(FXCollections.observableArrayList(driversRepository.findAll()));
			
		driver_fullname.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Drivers,String>, ObservableValue<String>>(){
			public ObservableValue<String> call (TableColumn.CellDataFeatures<Drivers, String> param){
				return new SimpleStringProperty(param.getValue().getDriver_fullname());
			}
		});
				
		driver_id.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Drivers, Number>, ObservableValue<Number>>(){
			public ObservableValue<Number> call (TableColumn.CellDataFeatures<Drivers, Number> param){
				return new SimpleIntegerProperty(param.getValue().getDriver_id());
			}
		});
		
		//Материалы
		
		mat_table.setItems(FXCollections.observableArrayList(materialsRepository.findAll()));
			
		mat_name.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Materials,String>, ObservableValue<String>>(){
			public ObservableValue<String> call (TableColumn.CellDataFeatures<Materials, String> param){
				return new SimpleStringProperty(param.getValue().getMat_name());
			}
		});
				
		mat_id.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Materials, Number>, ObservableValue<Number>>(){
			public ObservableValue<Number> call (TableColumn.CellDataFeatures<Materials, Number> param){
				return new SimpleIntegerProperty(param.getValue().getMat_id());
			}
		});		
	}
}