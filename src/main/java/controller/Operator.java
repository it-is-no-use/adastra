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

public class Operator {

	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	UsersRepository usersRepository = context.getBean(UsersRepository.class);
	ObjectsRepository objectsRepository = context.getBean(ObjectsRepository.class);
	DriversRepository driversRepository = context.getBean(DriversRepository.class);
	TrucksRepository trucksRepository = context.getBean(TrucksRepository.class);
	MaterialsRepository materialsRepository = context.getBean(MaterialsRepository.class);
	JobRepository jobRepository = context.getBean(JobRepository.class);
	
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
		
		user = usersRepository.findOne(Entrance.i);
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
	}
	
	@FXML
	private void job_edit() {
		
		number_txt.setText(j_table.getSelectionModel().getSelectedItem().getJ_id().toString());
		date_txt.setText(j_table.getSelectionModel().getSelectedItem().getJ_when().toString());
		weight_txt.setText(j_table.getSelectionModel().getSelectedItem().getWeight().toString());
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
		
	@FXML
	public void initialize() {
	
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
	}
}