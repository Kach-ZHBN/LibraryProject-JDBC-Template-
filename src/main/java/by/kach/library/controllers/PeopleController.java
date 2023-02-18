package by.kach.library.controllers;

import by.kach.library.dao.PersonDAO;
import by.kach.library.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model){
        /**
         * Метод обрабатывает GET-запрос от сервера. В модель помещается список объектов, которые
         * получаем из БД. Возвращаем представление, в котором Thymeleaf разбирается с полученным списком.
         */
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show (@PathVariable("id") int id, Model model){
        /**
         * Метод обрабатывает GET-запрос от сервера. Из URL'а извлекаем id,
         * по которому извлекается из БД необходимый объект Person. Затем мы передаем этот
         * объект с помощью Model в представление, а там уже Thymeleaf принимает его и отображает
         */
        model.addAttribute("person", personDAO.show(id));
        //Извлечени одного человека из ДАО и передать на отображение в представление
        model.addAttribute("books", personDAO.getPersonsBooks(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson (Model model){
        /**
        *Метод обрабатывает GET-запрос от сервера. Метод предназначенный для отправления страницы для создания человека.
        *С Thymelef'ом удобно работать, если ему сразу отправить шаблон Person. Мы получим шаблон Person,
        *на HTML странице его заполним и вернем в Model готовый объект, который сможем достать в методе create
         */
        model.addAttribute("person", new Person());
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") Person person){
        /**
        *Из формы в представлении /people/new получаем объект Person по ключу person.
        *Объект в Model поместил Thymeleaf.
        *Добавляем полученного Person в БД
        *Можем вернуть страницу, на которой будет отбражен успех созданяи Person (String)
        *а можем ничего не возвращать (void), но все же стоит куда-то вернуться
        */
       personDAO.save(person);
       return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit (@PathVariable("id") int id, Model model){
        /**
         * Метод обрабатывает GET-запрос от сервера. Из URL'а извлекаем id,
         * по которому извлекается из БД необходимый объект Person. Затем мы передаем этот
         * объект с помощью Model в представление. В представлении Thymeleaf заполнит форму существующими
         * полями. После редактирования формы, отправляется PATH-метод
         */
        model.addAttribute("person", personDAO.show(id));
        //Извлечени одного человека из ДАО и передать на отображение в представление
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update (@ModelAttribute("person") Person person){
        personDAO.update(person);
        return ("redirect:/people");
    }

    @DeleteMapping("/{id}")
    public String delete (@PathVariable("id") int id){
        personDAO.delete(id);
        return "redirect:/people";
    }
}
