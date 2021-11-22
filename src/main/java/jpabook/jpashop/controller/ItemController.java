package jpabook.jpashop.web;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequiredArgsConstructor

public class ItemController {

	private final ItemService itemService;

	@GetMapping("/items/new")
	public String createForm(Model model){
		model.addAttribute("form",new BookForm());
		return "items/createItemForm";
	}

	@PostMapping("/items/new")
	public String create(BookForm form){
		Book book = new Book();

		book.setName(form.getName());
		book.setPrice(form.getPrice());
		book.setStockQuantity(form.getStockQuantity());
		book.setAuthor(form.getAuthor());
		book.setIsbn(form.getIsbn());
		
		itemService.saveItem(book);
		return "redirect:/";
	}

	@GetMapping("/items")
	public String list(Model model){
		List <Item> items = itemService.findItems();
		model.addAttribute("items",items);

		return "items/itemList";
	}

	@GetMapping("/items/{itemId}/edit")
	public String updateItemForm(@PathVariable("itemId") Long itemId, Model model){
		Book Item = (Book) itemService.findOne(itemId);

		Bookform form = new BookForm();
		form.setId(item.getId());
		form.setName(item.getName());
		form.setPrice(item.getPrice());
		form.setStockQuantity(item.getStockQuantity());
		form.setAuthor(item.getAuthor());
		form.setIsbn(item.getIsbn());

		model.addAttribute("form",form);
		return "items/updateItemForm";
	}

	@PostMapping("/items/{itemId}/edit")
	public String updateItem(@PathVariable("itemId") Long itemId, @ModelAttribute("form") BookForm form){

		//보안 위해 user가 item에 대해 권한이 있는지 체크해주는 로직이 서버에 있어야
		Book book = new Book();
		book.setIsbn(form.getIsbn());
		book.setName(form.getName());
		book.setPrice(form.getPrice());
		book.setStockQuantity(form.getStockQuantity());
		book.setAuthor(form.getAuthor());
		book.setIsbn(form.getIsbn());

		itemService.saveItem(book);
		return "redirect:items";
	}
}