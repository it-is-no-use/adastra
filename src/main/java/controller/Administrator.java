package controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import entity.Drivers;
import entity.Job;
import entity.Materials;
import entity.Objects;
import entity.Trucks;
import entity.Users;
import inquery.Expense;
import inquery.Storage;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
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

public class Administrator {

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
	
	//Вкладка "Рабочая зона"
	
	@FXML
	private TableView<Job> j_table;
	
	@FXML
	private TableColumn<Job,Number> j_id;
	
	@FXML
	private TableColumn<Job,Date> j_when;
	
	@FXML
	private TableColumn<Job,Number> weight;
	
	@FXML
	private TableColumn<Job,String> j_ob_name;
	
	@FXML
	private TableColumn<Job,String> j_driver_fullname;
	
	@FXML
	private TableColumn<Job,String> j_truck_name;
	
	@FXML
	private TableColumn<Job,String> j_gov_number;
	
	@FXML
	private TableColumn<Job,String> j_mat_name;
	
	@FXML
	private TableColumn<Job,String> j_user_fullname;
	
	@FXML
	private ComboBox ob_cmb;
	
	@FXML
	private ComboBox driver_cmb;
	
	@FXML
	private ComboBox truck_cmb;
	
	@FXML
	private ComboBox mat_cmb;
	
	@FXML
	private ComboBox user_cmb;
	
	@FXML
	private TextField number_txt;
	
	@FXML
	private TextField date_txt;
	
	@FXML
	private TextField weight_txt;
	
	@FXML
	private void job_add() throws ParseException {
		
		Job job = new Job();
		
		job.setJ_id(Integer.valueOf(number_txt.getText()));
		
		String string = date_txt.getText();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse(string);
		java.sql.Date sqlDate=new java.sql.Date(date.getTime());
		job.setJ_when(sqlDate);
	
		job.setWeight(Double.valueOf(weight_txt.getText()));
		
		Objects object = new Objects();
		object.setOb_id(ob_cmb.getSelectionModel().getSelectedIndex()+1);
		object.setOb_name(objectsRepository.findOne(ob_cmb.getSelectionModel().getSelectedIndex()+1).getOb_name());
		job.setObject(object);
		
		Drivers driver = new Drivers();
		driver.setDriver_id(driver_cmb.getSelectionModel().getSelectedIndex()+1);
		driver.setDriver_fullname(driversRepository.findOne(driver_cmb.getSelectionModel().getSelectedIndex()+1).getDriver_fullname());
		job.setDriver(driver);
		
		Trucks truck = new Trucks();
		truck.setTruck_id(truck_cmb.getSelectionModel().getSelectedIndex()+1);
		truck.setTruck_name(trucksRepository.findOne(truck_cmb.getSelectionModel().getSelectedIndex()+1).getTruck_name());
		truck.setGov_number(trucksRepository.findOne(truck_cmb.getSelectionModel().getSelectedIndex()+1).getGov_number());
		job.setTruck(truck);
		
		Materials material = new Materials();
		material.setMat_id(mat_cmb.getSelectionModel().getSelectedIndex()+1);
		material.setMat_name(materialsRepository.findOne(mat_cmb.getSelectionModel().getSelectedIndex()+1).getMat_name());
		job.setMaterial(material);
		
		Users user = new Users();
		user.setUser_id(user_cmb.getSelectionModel().getSelectedIndex()+1);
		user.setUser_fullname(usersRepository.findOne(user_cmb.getSelectionModel().getSelectedIndex()+1).getUser_fullname());
		user.setRole(usersRepository.findOne(user_cmb.getSelectionModel().getSelectedIndex()+1).getRole());
		user.setPassword(usersRepository.findOne(user_cmb.getSelectionModel().getSelectedIndex()+1).getPassword());
		job.setUser(user);
		
		jobRepository.save(job);
		j_table.setItems(FXCollections.observableArrayList(jobRepository.find10()));
		
		number_txt.clear();
		date_txt.clear();
		weight_txt.clear();
		ob_cmb.setValue(null);
		driver_cmb.setValue(null);
		truck_cmb.setValue(null);
		mat_cmb.setValue(null);
		user_cmb.setValue(null);
	}
	
	@FXML
	private void job_edit() {
		
		number_txt.setText(j_table.getSelectionModel().getSelectedItem().getJ_id().toString());
		date_txt.setText(j_table.getSelectionModel().getSelectedItem().getJ_when().toString());
		weight_txt.setText(j_table.getSelectionModel().getSelectedItem().getWeight().toString());
	}
	
	@FXML
	private void job_refresh() {
		
		j_table.setItems(FXCollections.observableArrayList(jobRepository.find10()));
		
		ob_cmb.getItems().clear();
		driver_cmb.getItems().clear();
		truck_cmb.getItems().clear();
		mat_cmb.getItems().clear();
		user_cmb.getItems().clear();
		
		for(int i=1;i<=objectsRepository.count();i++){
			ob_cmb.getItems().add(objectsRepository.findOne(i).getOb_name());
		}
		
		for(int i=1;i<=driversRepository.count();i++){
			driver_cmb.getItems().add(driversRepository.findOne(i).getDriver_fullname());
		}
		
		for(int i=1;i<=trucksRepository.count();i++){
			truck_cmb.getItems().add(trucksRepository.findOne(i));
		}
		
		for(int i=1;i<=materialsRepository.count();i++){
			mat_cmb.getItems().add(materialsRepository.findOne(i).getMat_name());
		}
		
		for(int i=1;i<=usersRepository.count();i++){
			user_cmb.getItems().add(usersRepository.findOne(i).getUser_fullname());
		}
	}
	
	//Вкладка "Редактирование работы"
	
	@FXML
	private TableView<Job> j_table1;
	
	@FXML
	private TableColumn<Job,Number> j_id1;
	
	@FXML
	private TableColumn<Job,Date> j_when1;
	
	@FXML
	private TableColumn<Job,Number> weight1;
	
	@FXML
	private TableColumn<Job,String> j_ob_name1;
	
	@FXML
	private TableColumn<Job,String> j_driver_fullname1;
	
	@FXML
	private TableColumn<Job,String> j_truck_name1;
	
	@FXML
	private TableColumn<Job,String> j_gov_number1;
	
	@FXML
	private TableColumn<Job,String> j_mat_name1;
	
	@FXML
	private TableColumn<Job,String> j_user_fullname1;
	
	@FXML
	private ComboBox ob_cmb1;
	
	@FXML
	private ComboBox driver_cmb1;
	
	@FXML
	private ComboBox truck_cmb1;
	
	@FXML
	private ComboBox mat_cmb1;
	
	@FXML
	private ComboBox user_cmb1;
	
	@FXML
	private TextField number_txt1;
	
	@FXML
	private TextField date_txt1;
	
	@FXML
	private TextField weight_txt1;
	
	@FXML
	private TextField interval1;
	
	@FXML
	private TextField interval2;
	
	@FXML
	private void red_build() throws ParseException {
	
		String string1 = interval1.getText();
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = format1.parse(string1);
		java.sql.Date sqlDate1 = new java.sql.Date(date1.getTime());
		
		String string2 = interval2.getText();
		DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
		Date date2 = format2.parse(string2);
		java.sql.Date sqlDate2 = new java.sql.Date(date2.getTime());
		
		j_table1.setItems(FXCollections.observableArrayList(jobRepository.findWhen(sqlDate1, sqlDate2)));
	}
	
	@FXML
	private void red_add() throws ParseException {
		
		Job job = new Job();
		
		job.setJ_id(Integer.valueOf(number_txt1.getText()));
		
		String string = date_txt1.getText();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse(string);
		java.sql.Date sqlDate=new java.sql.Date(date.getTime());
		job.setJ_when(sqlDate);
	
		job.setWeight(Double.valueOf(weight_txt1.getText()));
		
		Objects object = new Objects();
		object.setOb_id(ob_cmb1.getSelectionModel().getSelectedIndex()+1);
		object.setOb_name(objectsRepository.findOne(ob_cmb1.getSelectionModel().getSelectedIndex()+1).getOb_name());
		job.setObject(object);
		
		Drivers driver = new Drivers();
		driver.setDriver_id(driver_cmb1.getSelectionModel().getSelectedIndex()+1);
		driver.setDriver_fullname(driversRepository.findOne(driver_cmb1.getSelectionModel().getSelectedIndex()+1).getDriver_fullname());
		job.setDriver(driver);
		
		Trucks truck = new Trucks();
		truck.setTruck_id(truck_cmb1.getSelectionModel().getSelectedIndex()+1);
		truck.setTruck_name(trucksRepository.findOne(truck_cmb1.getSelectionModel().getSelectedIndex()+1).getTruck_name());
		truck.setGov_number(trucksRepository.findOne(truck_cmb1.getSelectionModel().getSelectedIndex()+1).getGov_number());
		job.setTruck(truck);
		
		Materials material = new Materials();
		material.setMat_id(mat_cmb1.getSelectionModel().getSelectedIndex()+1);
		material.setMat_name(materialsRepository.findOne(mat_cmb1.getSelectionModel().getSelectedIndex()+1).getMat_name());
		job.setMaterial(material);
		
		Users user = new Users();
		user.setUser_id(user_cmb1.getSelectionModel().getSelectedIndex()+1);
		user.setUser_fullname(usersRepository.findOne(user_cmb1.getSelectionModel().getSelectedIndex()+1).getUser_fullname());
		user.setRole(usersRepository.findOne(user_cmb1.getSelectionModel().getSelectedIndex()+1).getRole());
		user.setPassword(usersRepository.findOne(user_cmb1.getSelectionModel().getSelectedIndex()+1).getPassword());
		job.setUser(user);
		
		jobRepository.save(job);
		
		String string1 = interval1.getText();
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = format1.parse(string1);
		java.sql.Date sqlDate1 = new java.sql.Date(date1.getTime());
		
		String string2 = interval2.getText();
		DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
		Date date2 = format2.parse(string2);
		java.sql.Date sqlDate2 = new java.sql.Date(date2.getTime());
		
		j_table1.setItems(FXCollections.observableArrayList(jobRepository.findWhen(sqlDate1, sqlDate2)));
		
		number_txt1.clear();
		date_txt1.clear();
		weight_txt1.clear();
		ob_cmb1.setValue(null);
		driver_cmb1.setValue(null);
		truck_cmb1.setValue(null);
		mat_cmb1.setValue(null);
		user_cmb1.setValue(null);
	}
	
	@FXML
	private void red_edit() {
		number_txt1.setText(j_table1.getSelectionModel().getSelectedItem().getJ_id().toString());
		date_txt1.setText(j_table1.getSelectionModel().getSelectedItem().getJ_when().toString());
		weight_txt1.setText(j_table1.getSelectionModel().getSelectedItem().getWeight().toString());
	}
	
	@FXML
	private void red_remove() throws ParseException {
		jobRepository.delete(j_table1.getSelectionModel().getSelectedItem().getJ_id());
		
		String string1 = interval1.getText();
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = format1.parse(string1);
		java.sql.Date sqlDate1 = new java.sql.Date(date1.getTime());
		
		String string2 = interval2.getText();
		DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
		Date date2 = format2.parse(string2);
		java.sql.Date sqlDate2 = new java.sql.Date(date2.getTime());
		
		j_table1.setItems(FXCollections.observableArrayList(jobRepository.findWhen(sqlDate1, sqlDate2)));
	}
	
	//Вкладка "Запросы"
	
	//На складе
	
	@FXML
	private TableView<Storage> storage_table;
	
	@FXML
	private TableColumn<Storage,String> storage_mat;
	
	@FXML
	private TableColumn<Storage,Number> storage_weight;
	
	@FXML
	private void storage_build_now() {
		
		Storage [] storage = new Storage[jobRepository.find_storage_now().length];
		ArrayList<Storage> st = new ArrayList<Storage>();
		
		for(int i=0;i<jobRepository.find_storage_now().length;i++) {
			Storage storage1 = new Storage();
			storage1.setStorage_mat(jobRepository.find_storage_now()[i][0]);
			storage1.setStorage_weight(jobRepository.find_storage_now()[i][1]);
			storage[i]=storage1;
			st.add(i, storage1);
	}
		storage_table.setItems(FXCollections.observableArrayList(st));
	}
	
	@FXML
	private TextField storage_moment;
	
	@FXML
	private void storage_build_moment() throws ParseException {
		
		String string = storage_moment.getText();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse(string);
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		
		Storage [] storage = new Storage[jobRepository.find_storage_moment(sqlDate).length];
		ArrayList<Storage> st = new ArrayList<Storage>();
		
		for(int i=0;i<jobRepository.find_storage_moment(sqlDate).length;i++) {
			Storage storage1 = new Storage();
			storage1.setStorage_mat(jobRepository.find_storage_moment(sqlDate)[i][0]);
			storage1.setStorage_weight(jobRepository.find_storage_moment(sqlDate)[i][1]);
			storage[i]=storage1;
			st.add(i, storage1);
	}
		storage_table.setItems(FXCollections.observableArrayList(st));
	}
	
	@FXML
	private TextField storage_interval1;

	@FXML
	private TextField storage_interval2;
	
	@FXML
	private void storage_build_interval() throws ParseException {
		
		String string1 = storage_interval1.getText();
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = format1.parse(string1);
		java.sql.Date sqlDate1 = new java.sql.Date(date1.getTime());

		String string2 = storage_interval2.getText();
		DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
		Date date2 = format2.parse(string2);
		java.sql.Date sqlDate2 = new java.sql.Date(date2.getTime());
		
		Storage [] storage = new Storage[jobRepository.find_storage_interval(sqlDate1, sqlDate2).length];
		ArrayList<Storage> st = new ArrayList<Storage>();
		
		for(int i=0;i<jobRepository.find_storage_interval(sqlDate1, sqlDate2).length;i++) {
			Storage storage1 = new Storage();
			storage1.setStorage_mat(jobRepository.find_storage_interval(sqlDate1, sqlDate2)[i][0]);
			storage1.setStorage_weight(jobRepository.find_storage_interval(sqlDate1, sqlDate2)[i][1]);
			storage[i]=storage1;
			st.add(i, storage1);
	}
		storage_table.setItems(FXCollections.observableArrayList(st));
	}
	
	//Приход/расход в определённый период
	
	@FXML
	private TableView<Storage> comingconsumption_table;
	
	@FXML
	private TableColumn<Storage,String> comingconsumption_mat;
	
	@FXML
	private TableColumn<Storage,Number> comingconsumption_weight;
	
	@FXML
	private TextField coming_interval1;

	@FXML
	private TextField coming_interval2;
	
	@FXML
	private void coming_build_interval() throws ParseException {
		
		String string1 = coming_interval1.getText();
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = format1.parse(string1);
		java.sql.Date sqlDate1 = new java.sql.Date(date1.getTime());

		String string2 = coming_interval2.getText();
		DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
		Date date2 = format2.parse(string2);
		java.sql.Date sqlDate2 = new java.sql.Date(date2.getTime());
		
		Storage [] storage = new Storage[jobRepository.find_storage_coming(sqlDate1, sqlDate2).length];
		ArrayList<Storage> st = new ArrayList<Storage>();
		
		for(int i=0;i<jobRepository.find_storage_coming(sqlDate1, sqlDate2).length;i++) {
			Storage storage1 = new Storage();
			storage1.setStorage_mat(jobRepository.find_storage_coming(sqlDate1, sqlDate2)[i][0]);
			storage1.setStorage_weight(jobRepository.find_storage_coming(sqlDate1, sqlDate2)[i][1]);
			storage[i]=storage1;
			st.add(i, storage1);
	}
		comingconsumption_table.setItems(FXCollections.observableArrayList(st));
	}
	
	@FXML
	private TextField consumption_interval1;

	@FXML
	private TextField consumption_interval2;
	
	@FXML
	private void consumption_build_interval() throws ParseException {
		
		String string1 = consumption_interval1.getText();
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = format1.parse(string1);
		java.sql.Date sqlDate1 = new java.sql.Date(date1.getTime());

		String string2 = consumption_interval2.getText();
		DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
		Date date2 = format2.parse(string2);
		java.sql.Date sqlDate2 = new java.sql.Date(date2.getTime());
		
		Storage [] storage = new Storage[jobRepository.find_storage_consumption(sqlDate1, sqlDate2).length];
		ArrayList<Storage> st = new ArrayList<Storage>();
		
		for(int i=0;i<jobRepository.find_storage_consumption(sqlDate1, sqlDate2).length;i++) {
			Storage storage1 = new Storage();
			storage1.setStorage_mat(jobRepository.find_storage_consumption(sqlDate1, sqlDate2)[i][0]);
			storage1.setStorage_weight(jobRepository.find_storage_consumption(sqlDate1, sqlDate2)[i][1]);
			storage[i]=storage1;
			st.add(i, storage1);
	}
		comingconsumption_table.setItems(FXCollections.observableArrayList(st));
	}
	
	//Просмотр рабочей таблицы
	
	@FXML
	private TableView<Job> view_table;
	
	@FXML
	private TableColumn<Job,Number> view_id;
	
	@FXML
	private TableColumn<Job,Date> view_when;
	
	@FXML
	private TableColumn<Job,Number> view_weight;
	
	@FXML
	private TableColumn<Job,String> view_ob_name;
	
	@FXML
	private TableColumn<Job,String> view_driver_fullname;
	
	@FXML
	private TableColumn<Job,String> view_truck_name;
	
	@FXML
	private TableColumn<Job,String> view_gov_number;
	
	@FXML
	private TableColumn<Job,String> view_mat_name;
	
	@FXML
	private TableColumn<Job,String> view_user_fullname;
	
	@FXML
	private TextField view_interval1;
	
	@FXML
	private TextField view_interval2;
	
	@FXML
	private void view_build() throws ParseException {
	
		String string1 = view_interval1.getText();
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = format1.parse(string1);
		java.sql.Date sqlDate1 = new java.sql.Date(date1.getTime());
		
		String string2 = view_interval2.getText();
		DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
		Date date2 = format2.parse(string2);
		java.sql.Date sqlDate2 = new java.sql.Date(date2.getTime());
		
		view_table.setItems(FXCollections.observableArrayList(jobRepository.findWhen(sqlDate1, sqlDate2)));
	}
	
	//Траты на объектах
	
	@FXML
	private TableView<Expense> expense_table;
	
	@FXML
	private TableColumn<Expense,String> expense_ob_name;

	@FXML
	private TableColumn<Expense,String> expense_mat_name;
	
	@FXML
	private TableColumn<Expense,Number> expense_weight;
	
	@FXML
	private void expense_build() {
		Expense [] expense = new Expense[jobRepository.find_spending_now().length];
		ArrayList<Expense> st = new ArrayList<Expense>();
		
		for(int i=0; i<jobRepository.find_spending_now().length; i++) {
				Expense expense1 = new Expense();
				expense1.setExpense_obj(jobRepository.find_spending_now()[i][0][0]);
				expense1.setExpense_mat(jobRepository.find_spending_now()[i][1][0]);
				expense1.setExpense_weight(jobRepository.find_spending_now()[i][2][0]);
				expense[i]=expense1;
				st.add(i, expense1);		
	}
		expense_table.setItems(FXCollections.observableArrayList(st));
	}
	
	@FXML
	private TextField expense_interval1;
	
	@FXML
	private TextField expense_interval2;
	
	@FXML
	private void expense_build_interval() throws ParseException {
		
		String string1 = expense_interval1.getText();
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = format1.parse(string1);
		java.sql.Date sqlDate1 = new java.sql.Date(date1.getTime());

		String string2 = expense_interval2.getText();
		DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
		Date date2 = format2.parse(string2);
		java.sql.Date sqlDate2 = new java.sql.Date(date2.getTime());
		
		Expense [] expense = new Expense[jobRepository.find_spending_interval(sqlDate1, sqlDate2).length];
		ArrayList<Expense> st = new ArrayList<Expense>();
		
		for(int i=0; i<jobRepository.find_spending_interval(sqlDate1, sqlDate2).length; i++) {
			Expense expense1 = new Expense();
			expense1.setExpense_obj(jobRepository.find_spending_interval(sqlDate1, sqlDate2)[i][0][0]);
			expense1.setExpense_mat(jobRepository.find_spending_interval(sqlDate1, sqlDate2)[i][1][0]);
			expense1.setExpense_weight(jobRepository.find_spending_interval(sqlDate1, sqlDate2)[i][2][0]);
			expense[i]=expense1;
			st.add(i, expense1);		
		}

		expense_table.setItems(FXCollections.observableArrayList(st));
	}
	
	//Вкладка "Пользователи"
	
	@FXML
	private TableView<Users> user_table;
	
	@FXML
	private TableColumn<Users,Number> user_id;
	
	@FXML
	private TableColumn<Users,String> user_fullname;
	
	@FXML
	private TableColumn<Users,String> role;
	
	@FXML
	private TableColumn<Users,String> login;
	
	@FXML
	private TableColumn<Users,String> password;
	
	@FXML
	private TextField usernumber_txt;
	
	@FXML
	private TextField username_txt;
	
	@FXML
	private ComboBox userrole_cmb;
	
	@FXML
	private TextField userlog_txt;
	
	@FXML
	private TextField userpassw_txt;
	
	@FXML
	private void user_add() {
		
		Users user= new Users();
		
		user.setUser_id(Integer.valueOf(usernumber_txt.getText()));
		user.setUser_fullname(username_txt.getText());
		user.setRole(userrole_cmb.getSelectionModel().getSelectedItem().toString());
		user.setLogin(userlog_txt.getText());
		user.setPassword(userpassw_txt.getText());
		usersRepository.save(user);
		user_table.setItems(FXCollections.observableArrayList(usersRepository.findAll()));

		usernumber_txt.clear();
		username_txt.clear();
		userrole_cmb.setValue(null);
		userlog_txt.clear();
		userpassw_txt.clear();
	}
	
	@FXML
	private void user_edit() {
		
		usernumber_txt.setText(user_table.getSelectionModel().getSelectedItem().getUser_id().toString());
		username_txt.setText(user_table.getSelectionModel().getSelectedItem().getUser_fullname().toString());
		userpassw_txt.setText(user_table.getSelectionModel().getSelectedItem().getPassword().toString());
		userlog_txt.setText(user_table.getSelectionModel().getSelectedItem().getLogin().toString());
	}
	
	@FXML
	private void user_remove() {
		
		usersRepository.delete(user_table.getSelectionModel().getSelectedItem().getUser_id());
		user_table.setItems(FXCollections.observableArrayList(usersRepository.findAll()));
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
		
		//Вкладка "Рабочая зона"
		
		j_table.setItems(FXCollections.observableArrayList(jobRepository.find10()));
		
		j_id.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Job, Number>, ObservableValue<Number>>(){
			public ObservableValue<Number> call (TableColumn.CellDataFeatures<Job, Number> param){
				return new SimpleIntegerProperty(param.getValue().getJ_id());
			}
		});
		
		j_when.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Job,Date>, ObservableValue<Date>>(){
			public ObservableValue<Date> call (TableColumn.CellDataFeatures<Job, Date> param){
				return new SimpleObjectProperty(param.getValue().getJ_when());
			}
		});
		
		weight.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Job, Number>, ObservableValue<Number>>(){
			public ObservableValue<Number> call (TableColumn.CellDataFeatures<Job, Number> param){
				return new SimpleDoubleProperty(param.getValue().getWeight());
			}
		});
		
		j_ob_name.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Job,String>, ObservableValue<String>>(){
			public ObservableValue<String> call (TableColumn.CellDataFeatures<Job, String> param){
				return new SimpleStringProperty(param.getValue().getObject().getOb_name());
			}
		});
		
		j_driver_fullname.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Job,String>, ObservableValue<String>>(){
			public ObservableValue<String> call (TableColumn.CellDataFeatures<Job, String> param){
				return new SimpleStringProperty(param.getValue().getDriver().getDriver_fullname());
			}
		});
		
		j_truck_name.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Job,String>, ObservableValue<String>>(){
			public ObservableValue<String> call (TableColumn.CellDataFeatures<Job, String> param){
				return new SimpleStringProperty(param.getValue().getTruck().getTruck_name());
			}
		});
		
		j_gov_number.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Job,String>, ObservableValue<String>>(){
			public ObservableValue<String> call (TableColumn.CellDataFeatures<Job, String> param){
				return new SimpleStringProperty(param.getValue().getTruck().getGov_number());
			}
		});
		
		j_mat_name.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Job,String>, ObservableValue<String>>(){
			public ObservableValue<String> call (TableColumn.CellDataFeatures<Job, String> param){
				return new SimpleStringProperty(param.getValue().getMaterial().getMat_name());
			}
		});
		
		j_user_fullname.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Job,String>, ObservableValue<String>>(){
			public ObservableValue<String> call (TableColumn.CellDataFeatures<Job, String> param){
				return new SimpleStringProperty(param.getValue().getUser().getUser_fullname());
			}
		});
		
		for(int i=1;i<=objectsRepository.count();i++){
			ob_cmb.getItems().add(objectsRepository.findOne(i).getOb_name());
		}
		
		for(int i=1;i<=driversRepository.count();i++){
			driver_cmb.getItems().add(driversRepository.findOne(i).getDriver_fullname());
		}
		
		for(int i=1;i<=trucksRepository.count();i++){
			truck_cmb.getItems().add(trucksRepository.findOne(i));
		}
		
		for(int i=1;i<=materialsRepository.count();i++){
			mat_cmb.getItems().add(materialsRepository.findOne(i).getMat_name());
		}
		
		for(int i=1;i<=usersRepository.count();i++){
			user_cmb.getItems().add(usersRepository.findOne(i).getUser_fullname());
		}
		
		//Вкладка "Запросы"
		
		//На складе
		
		storage_mat.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Storage, String>, ObservableValue<String>>(){
			public ObservableValue<String> call (TableColumn.CellDataFeatures<Storage, String> param){
				return new SimpleStringProperty(param.getValue().getStorage_mat());
			}
		});
		
		storage_weight.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Storage, Number>, ObservableValue<Number>>(){
			public ObservableValue<Number> call (TableColumn.CellDataFeatures<Storage, Number> param){
				return new SimpleDoubleProperty(param.getValue().getStorage_weight());
			}
		});
		
		//Приход/Расход в определённый период
		
		comingconsumption_mat.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Storage, String>, ObservableValue<String>>(){
			public ObservableValue<String> call (TableColumn.CellDataFeatures<Storage, String> param){
				return new SimpleStringProperty(param.getValue().getStorage_mat());
			}
		});
		
		comingconsumption_weight.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Storage, Number>, ObservableValue<Number>>(){
			public ObservableValue<Number> call (TableColumn.CellDataFeatures<Storage, Number> param){
				return new SimpleDoubleProperty(param.getValue().getStorage_weight());
			}
		});
		
		//Просмотр рабочей таблицы
		
		view_id.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Job, Number>, ObservableValue<Number>>(){
			public ObservableValue<Number> call (TableColumn.CellDataFeatures<Job, Number> param){
				return new SimpleIntegerProperty(param.getValue().getJ_id());
			}
		});
		
		view_when.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Job,Date>, ObservableValue<Date>>(){
			public ObservableValue<Date> call (TableColumn.CellDataFeatures<Job, Date> param){
				return new SimpleObjectProperty(param.getValue().getJ_when());
			}
		});
		
		view_weight.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Job, Number>, ObservableValue<Number>>(){
			public ObservableValue<Number> call (TableColumn.CellDataFeatures<Job, Number> param){
				return new SimpleDoubleProperty(param.getValue().getWeight());
			}
		});
		
		view_ob_name.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Job,String>, ObservableValue<String>>(){
			public ObservableValue<String> call (TableColumn.CellDataFeatures<Job, String> param){
				return new SimpleStringProperty(param.getValue().getObject().getOb_name());
			}
		});
		
		view_driver_fullname.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Job,String>, ObservableValue<String>>(){
			public ObservableValue<String> call (TableColumn.CellDataFeatures<Job, String> param){
				return new SimpleStringProperty(param.getValue().getDriver().getDriver_fullname());
			}
		});
		
		view_truck_name.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Job,String>, ObservableValue<String>>(){
			public ObservableValue<String> call (TableColumn.CellDataFeatures<Job, String> param){
				return new SimpleStringProperty(param.getValue().getTruck().getTruck_name());
			}
		});
		
		view_gov_number.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Job,String>, ObservableValue<String>>(){
			public ObservableValue<String> call (TableColumn.CellDataFeatures<Job, String> param){
				return new SimpleStringProperty(param.getValue().getTruck().getGov_number());
			}
		});
		
		view_mat_name.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Job,String>, ObservableValue<String>>(){
			public ObservableValue<String> call (TableColumn.CellDataFeatures<Job, String> param){
				return new SimpleStringProperty(param.getValue().getMaterial().getMat_name());
			}
		});
		
		view_user_fullname.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Job,String>, ObservableValue<String>>(){
			public ObservableValue<String> call (TableColumn.CellDataFeatures<Job, String> param){
				return new SimpleStringProperty(param.getValue().getUser().getUser_fullname());
			}
		});
		
		//Траты на объектах
		
		expense_ob_name.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Expense, String>, ObservableValue<String>>(){
			public ObservableValue<String> call (TableColumn.CellDataFeatures<Expense, String> param){
				return new SimpleStringProperty(param.getValue().getExpense_obj());
			}
		});
		
		expense_mat_name.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Expense, String>, ObservableValue<String>>(){
			public ObservableValue<String> call (TableColumn.CellDataFeatures<Expense, String> param){
				return new SimpleStringProperty(param.getValue().getExpense_mat());
			}
		});
		
		expense_weight.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Expense, Number>, ObservableValue<Number>>(){
			public ObservableValue<Number> call (TableColumn.CellDataFeatures<Expense, Number> param){
				return new SimpleDoubleProperty(param.getValue().getExpense_weight());
			}
		});
		
		//Вкладка "Редактирование работы"
		
		j_id1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Job, Number>, ObservableValue<Number>>(){
			public ObservableValue<Number> call (TableColumn.CellDataFeatures<Job, Number> param){
				return new SimpleIntegerProperty(param.getValue().getJ_id());
			}
		});
		
		j_when1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Job,Date>, ObservableValue<Date>>(){
			public ObservableValue<Date> call (TableColumn.CellDataFeatures<Job, Date> param){
				return new SimpleObjectProperty(param.getValue().getJ_when());
			}
		});
		
		weight1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Job, Number>, ObservableValue<Number>>(){
			public ObservableValue<Number> call (TableColumn.CellDataFeatures<Job, Number> param){
				return new SimpleDoubleProperty(param.getValue().getWeight());
			}
		});
		
		j_ob_name1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Job,String>, ObservableValue<String>>(){
			public ObservableValue<String> call (TableColumn.CellDataFeatures<Job, String> param){
				return new SimpleStringProperty(param.getValue().getObject().getOb_name());
			}
		});
		
		j_driver_fullname1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Job,String>, ObservableValue<String>>(){
			public ObservableValue<String> call (TableColumn.CellDataFeatures<Job, String> param){
				return new SimpleStringProperty(param.getValue().getDriver().getDriver_fullname());
			}
		});
		
		j_truck_name1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Job,String>, ObservableValue<String>>(){
			public ObservableValue<String> call (TableColumn.CellDataFeatures<Job, String> param){
				return new SimpleStringProperty(param.getValue().getTruck().getTruck_name());
			}
		});
		
		j_gov_number1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Job,String>, ObservableValue<String>>(){
			public ObservableValue<String> call (TableColumn.CellDataFeatures<Job, String> param){
				return new SimpleStringProperty(param.getValue().getTruck().getGov_number());
			}
		});
		
		j_mat_name1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Job,String>, ObservableValue<String>>(){
			public ObservableValue<String> call (TableColumn.CellDataFeatures<Job, String> param){
				return new SimpleStringProperty(param.getValue().getMaterial().getMat_name());
			}
		});
		
		j_user_fullname1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Job,String>, ObservableValue<String>>(){
			public ObservableValue<String> call (TableColumn.CellDataFeatures<Job, String> param){
				return new SimpleStringProperty(param.getValue().getUser().getUser_fullname());
			}
		});
		
		for(int i=1;i<=objectsRepository.count();i++){
			ob_cmb1.getItems().add(objectsRepository.findOne(i).getOb_name());
		}
		
		for(int i=1;i<=driversRepository.count();i++){
			driver_cmb1.getItems().add(driversRepository.findOne(i).getDriver_fullname());
		}
		
		for(int i=1;i<=trucksRepository.count();i++){
			truck_cmb1.getItems().add(trucksRepository.findOne(i));
		}
		
		for(int i=1;i<=materialsRepository.count();i++){
			mat_cmb1.getItems().add(materialsRepository.findOne(i).getMat_name());
		}
		
		for(int i=1;i<=usersRepository.count();i++){
			user_cmb1.getItems().add(usersRepository.findOne(i).getUser_fullname());
		}
		
		//Вкладка "Пользователи"
		
		user_table.setItems(FXCollections.observableArrayList(usersRepository.findAll()));
		
		user_id.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Users, Number>, ObservableValue<Number>>(){
			public ObservableValue<Number> call (TableColumn.CellDataFeatures<Users, Number> param){
				return new SimpleIntegerProperty(param.getValue().getUser_id());
			}
		});
		
		user_fullname.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Users,String>, ObservableValue<String>>(){
			public ObservableValue<String> call (TableColumn.CellDataFeatures<Users, String> param){
				return new SimpleStringProperty(param.getValue().getUser_fullname());
			}
		});
		
		role.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Users,String>, ObservableValue<String>>(){
			public ObservableValue<String> call (TableColumn.CellDataFeatures<Users, String> param){
				return new SimpleStringProperty(param.getValue().getRole());
			}
		});
		
		password.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Users,String>, ObservableValue<String>>(){
			public ObservableValue<String> call (TableColumn.CellDataFeatures<Users, String> param){
				return new SimpleStringProperty(param.getValue().getPassword());
			}
		});
		
		login.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Users,String>, ObservableValue<String>>(){
			public ObservableValue<String> call (TableColumn.CellDataFeatures<Users, String> param){
				return new SimpleStringProperty(param.getValue().getLogin());
			}
		});
		userrole_cmb.getItems().addAll("администратор","планировщик","оператор");
	}
}