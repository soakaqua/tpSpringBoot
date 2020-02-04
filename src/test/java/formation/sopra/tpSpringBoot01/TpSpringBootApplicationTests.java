package formation.sopra.tpSpringBoot01;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import formation.sopra.tpSpringBoot01.repositories.SalleRepository;

@SpringBootTest
class TpSpringBootApplicationTests {

	@Autowired
	private SalleRepository salleRepository;
	
	@Test
	void contextLoads() {
		assertNotNull(salleRepository);
	}

}
