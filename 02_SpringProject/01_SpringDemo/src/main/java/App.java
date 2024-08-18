
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import project.vbb.interfaces.Car;

public class App {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		Car myCar = context.getBean("corolla", Car.class);
		myCar.specs();
		
		context.close();
		
	}

}
