package com.cc.scoot;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin()
public class TodoController {

	@Autowired
	TodoRepository todoRepo;

	@PostMapping(value = "/add")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void addEntry(@RequestBody Todo todo) {
		Todo newEntry = new Todo();
		newEntry.setEntry(todo.getEntry());
		newEntry.setDueDate(todo.getDueDate());
		newEntry.setPriority(todo.getPriority());
		newEntry.setOrder(todo.getOrder());
		this.todoRepo.save(newEntry);
	}

	@GetMapping(value = "/getAll")
	@ResponseStatus(HttpStatus.OK)
	public String getAll() {
		JSONArray response = new JSONArray();
		this.todoRepo.findAll().forEach(entry -> response.put(entry.toJsonObject()));
		return response.toString();
	}

	@GetMapping(value = "/search/{entry}")
	@ResponseStatus(HttpStatus.OK)
	public String searchByEntry(@PathVariable("entry") String entry) {
		JSONArray response = new JSONArray();
		this.todoRepo.findAll().forEach(e -> {
			if (e.getPriority().contains(entry)) {
				response.put(e.toJsonObject());
			}
		});
		return response.toString();
	}

	@DeleteMapping(value = "/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable("id") int id) {

		this.todoRepo.deleteById(id);
	}

	@PutMapping(value = "/update/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable("id") int id, @RequestBody Todo todo) {

		Todo foundTodo = this.todoRepo.findById(id).get();
		foundTodo.setEntry(todo.getEntry());
		foundTodo.setDueDate(todo.getDueDate());
		foundTodo.setPriority(todo.getPriority());
		foundTodo.setOrder(todo.getOrder());
		this.todoRepo.save(foundTodo);
	}
}
