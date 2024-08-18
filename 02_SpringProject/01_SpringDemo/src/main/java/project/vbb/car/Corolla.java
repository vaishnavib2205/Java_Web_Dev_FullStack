package project.vbb.car;

import org.springframework.stereotype.Component;

import project.vbb.interfaces.Car;

@Component
public class Corolla implements Car {

	@Override
	public void specs() {
		System.out.println("Sedan from Toyota");

	}

}
