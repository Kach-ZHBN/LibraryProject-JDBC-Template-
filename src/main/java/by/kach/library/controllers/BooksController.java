package by.kach.library.controllers;

import by.kach.library.dao.BooksDAO;
import by.kach.library.models.Book;
import by.kach.library.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BooksDAO booksDAO;

    @Autowired
    public BooksController(BooksDAO booksDAO) {
        this.booksDAO = booksDAO;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("books", booksDAO.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("book", booksDAO.show(id));
        model.addAttribute("people", booksDAO.getPeople());
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(Model model){
        model.addAttribute("book", new Book());
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") Book book){

        booksDAO.create(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("book", booksDAO.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") Book book){
        booksDAO.update(book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        booksDAO.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/setOwner")
    public String setBooksOwner(@PathVariable("id") int id, @ModelAttribute("book") Book book){
        booksDAO.setOwner(book);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/freeOwner")
    public String freeBooksOwner(@PathVariable("id") int id, @ModelAttribute("book") Book book){
        booksDAO.freeOwner(book);
        return "redirect:/books/" + id;
    }
}
