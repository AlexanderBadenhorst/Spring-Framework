package tacos.chap1.web;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import lombok.extern.slf4j.Slf4j;
import tacos.chap1.Ingredients;
import tacos.chap1.Ingredients.Type;
import tacos.chap1.TacoOrder;
import tacos.chap1.Taco;



@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")

public class DesignTacoController {

	@ModelAttribute
	public void addIngredientsToModel(Model model) {
		List<Ingredients> ingredients = Arrays.asList(new Ingredients("FLTO", "Flour Tortilla", Type.WRAP),
				new Ingredients("COTO", "Corn Tortilla", Type.WRAP), new Ingredients("GRBF", "Ground Beef", Type.PROTEIN),
				new Ingredients("CARN", "Carnitas", Type.PROTEIN),
				new Ingredients("TMTO", "Diced Tomatoes", Type.VEGGIES), new Ingredients("LETC", "Lettuce", Type.VEGGIES),
				new Ingredients("CHED", "Cheddar", Type.CHEESE), new Ingredients("JACK", "Monterrey Jack", Type.CHEESE),
				new Ingredients("SLSA", "Salsa", Type.SAUCE), new Ingredients("SRCR", "Sour Cream", Type.SAUCE));
		Type[] types = Ingredients.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
		}
	}

	@ModelAttribute(name = "tacoOrder")
	public TacoOrder order() {
		return new TacoOrder();
	}

	@ModelAttribute(name = "taco")
	public Taco taco() {
		return new Taco();
	}

	@GetMapping
	public String showDesignForm() {
		return "design";
	}

	private Iterable<Ingredients> filterByType(List<Ingredients> ingredients, Type type) {
		return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
	}
}
