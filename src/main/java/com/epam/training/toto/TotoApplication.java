package com.epam.training.toto;

import com.epam.training.toto.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TotoApplication implements CommandLineRunner {

    @Autowired
    private View view;

    public static void main(String[] args) {
        SpringApplication.run(TotoApplication.class, args);
    }

    @Override
    public void run(String[] args) {
        view.start();
    }
}
