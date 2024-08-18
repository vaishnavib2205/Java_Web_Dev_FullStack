package project.vbb.car;

import org.springframework.stereotype.Component;

import project.vbb.interfaces.Car;

@Component
public class Swift implements Car {

	@Override
	public void specs() {
		System.out.println("Hatchback from suzuki");

	}

}
