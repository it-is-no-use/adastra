package controller;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import entity.Users;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import repository.DriversRepository;
import repository.JobRepository;
import repository.MaterialsRepository;
import repository.ObjectsRepository;
import repository.TrucksRepository;
import repository.UsersRepository;

public class Entrance {

	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	UsersRepository usersRepository = context.getBean(UsersRepository.class);
	ObjectsRepository objectsRepository = context.getBean(ObjectsRepository.class);
	DriversRepository driversRepository = context.getBean(DriversRepository.class);
	TrucksRepository trucksRepository = context.getBean(TrucksRepository.class);
	MaterialsRepository materialsRepository = context.getBean(MaterialsRepository.class);
	JobRepository jobRepository = context.getBean(JobRepository.class);
	
	@FXML
    public static Stage STAGE;
	
	@FXML
	private TextField login;
	
	@FXML
	private PasswordField password;
	
	@FXML
	private Label label;
	
	public static int i;
	
	@FXML
	private void user_entrance() throws IOException {
		
		boolean logic=false;
		
		Users human = new Users();
		
		human.setLogin(login.getText());
		human.setPassword(password.getText());
		
		Users user = new Users();
		
		for(i=1; i<=usersRepository.count();i++){
			user = usersRepository.findOne(i);
			logic=(human.getLogin().equals(user.getLogin()))&&(human.getPassword().equals(user.getPassword()));
			
			if(logic==true){
				if(user.getRole().equals("администратор")){
				String fxmlFile = "/main_admin.fxml";
				FXMLLoader loader = new FXMLLoader();
				Parent root = loader.load(getClass().getResourceAsStream(fxmlFile));
				Scene scene = new Scene(root,850,650);
				STAGE.setScene(scene);
				STAGE.setTitle("Adastra");
				STAGE.getIcons().add(new Image("/star-32.png"));
				}
					if(user.getRole().equals("оператор")){
						String fxmlFile = "/main_oper.fxml";
						FXMLLoader loader = new FXMLLoader();
						Parent root = loader.load(getClass().getResourceAsStream(fxmlFile));
						Scene scene = new Scene(root,850,650);
						STAGE.setScene(scene);
						STAGE.setTitle("Adastra");
						STAGE.getIcons().add(new Image("/star-32.png"));
					}
				if(user.getRole().equals("планировщик")){
					String fxmlFile = "/main_plan.fxml";
					FXMLLoader loader = new FXMLLoader();
					Parent root = loader.load(getClass().getResourceAsStream(fxmlFile));
					Scene scene = new Scene(root,850,650);
					STAGE.setScene(scene);
					STAGE.setTitle("Adastra");
					STAGE.getIcons().add(new Image("/star-32.png"));
				}
			break;
			}
		}
		
		if(logic==false){
			label.setText("¬ход не выполнен, попробуйте снова");
			login.clear();
			password.clear();
		}
	}
	
	@FXML
	public void initialize() {
		login.setOnMouseClicked(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event){
				label.setText(null);
			}
		});
	}
}