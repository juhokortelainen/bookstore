package hh.swd20.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.swd20.Bookstore.domain.Category;
import hh.swd20.Bookstore.domain.CategoryRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CategoryRepositoryTest {

	@Autowired
	private CategoryRepository categoryRepository;

	@Test
	public void createNewCategory() {
		Category category = new Category("Fantasy");
		categoryRepository.save(category);
		assertThat(category.getName()).isNotNull();
	}
	
	@Test
	public void deleteCategory() {
		Category category = new Category("Horror");
		categoryRepository.delete(category);
		assertThat(category).isNull();
	}
	
	@Test
	public void searchCategory() {
		Category category = new Category("Fantasy");
		categoryRepository.save(category);
		assertThat(categoryRepository.findById(category.getCategoryid())).isNotNull();
	}

}
