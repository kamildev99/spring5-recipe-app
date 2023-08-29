package guru.springframework.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeTest {

    Recipe recipe;

    @Before
    public void setUp() throws Exception {
        recipe = new Recipe();
    }

    @Test
    public void getId() throws Exception {
        Long idValue = 55L;
        recipe.setId(idValue);

        assertEquals( idValue,recipe.getId());
    }

    @Test
    public void getDescription() throws Exception {
        String descriptionValue = "test value description";
        recipe.setDescription(descriptionValue);

        assertEquals(descriptionValue, recipe.getDescription());
    }

    @Test
    public void getSource() throws Exception {
        String sourceVal = "source test value";
        recipe.setSource(sourceVal);

        assertEquals(sourceVal, recipe.getSource());

    }
}