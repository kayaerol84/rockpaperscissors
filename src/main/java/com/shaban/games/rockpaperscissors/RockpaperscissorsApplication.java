package com.shaban.games.rockpaperscissors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class RockpaperscissorsApplication {

    public static void main(String[] args) {
		SpringApplication.run(RockpaperscissorsApplication.class, args);
	}

    /*@Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

            playRockPaperScissors();

        };
    }

    private static void playRockPaperScissors() {
        final Scanner scanner = new Scanner(System.in);

        System.out.print("How many times do you want to play this game? ");
        final int nTimes = getNextInt(scanner, "Please enter a number. How many times do you want to play this game? ");

        IntStream.rangeClosed(1, nTimes).forEach( e -> gameService.playRockPaperScissors(scanner) );

        System.out.println("Bye bye!");
    }*/
}
