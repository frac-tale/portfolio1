package com.example.Sample.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.Sample.entity.Database;
import com.example.Sample.form.DatabaseForm;
import com.example.Sample.service.DatabaseService;

@Controller
@RequestMapping("/database")
public class DatabaseController {
	
	private final DatabaseService service;
	public DatabaseController(DatabaseService service) {
		this.service = service;
	}
	
	@ModelAttribute
	public DatabaseForm setUpForm() {
		DatabaseForm form = new DatabaseForm();
		return form;
	}
	
	@GetMapping
	public String showList(DatabaseForm DatabaseForm, Model model) {
		DatabaseForm.setNewData(true);
		Iterable<Database> list = service.selectAll();
		model.addAttribute("list", list);
		
		return "crud";
	}
	
	@PostMapping("/insert") 
	public String insert(@Validated DatabaseForm databaseForm, BindingResult bindingResult,
			Model model, RedirectAttributes redirectAttributes) {
		Database database = new Database();
		database.setName(databaseForm.getName());
		database.setAge(databaseForm.getAge());
		if(!bindingResult.hasErrors()) {
			service.insertDatabase(database);
			redirectAttributes.addFlashAttribute("complete", "登録が完了しました。");
			return "redirect:/database";
		} else {
			return showList(databaseForm, model);
		}
	}
	
	@GetMapping("/{id}")
	public String showUpdate(DatabaseForm databaseForm, @PathVariable Integer id, Model model) {
		Optional<Database> databaseOpt = service.selectOneById(id);
		Optional<DatabaseForm> databaseFormOpt = databaseOpt.map(t -> makeDatabaseForm(t));
		if(databaseFormOpt.isPresent()) {
			databaseForm = databaseFormOpt.get();
		}
		
		//更新対象の情報を抜き取る
		model.addAttribute("databases", databaseOpt.get());
		
		makeUpdateModel(databaseForm, model);		
		return "crud";
	}
	
	private void makeUpdateModel(DatabaseForm databaseForm, Model model) {
		model.addAttribute("id", databaseForm.getId());
		databaseForm.setNewData(false);
		model.addAttribute("databaseForm", databaseForm);
		
	}
	
	@PostMapping("/update")
	public String update(@Validated DatabaseForm databaseForm, BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {
		Database database = makeDatabase(databaseForm);
		if(!result.hasErrors()) {
			service.updateDatabase(database);
			redirectAttributes.addFlashAttribute("complete", "更新が完了しました。");
			return "redirect:/database/" + database.getId();
		} else { 
			makeUpdateModel(databaseForm, model);
			return "crud";
		}
	}
	
	private Database makeDatabase(DatabaseForm databaseForm) {
		Database database = new Database();
		database.setId(databaseForm.getId());
		database.setName(databaseForm.getName());
		database.setAge(databaseForm.getAge());
		return database;
	}
	
	private DatabaseForm makeDatabaseForm(Database database) {
		DatabaseForm form = new DatabaseForm();
		form.setId(database.getId());
		form.setName(database.getName());
		form.setAge(database.getAge());
		form.setNewData(false);
		return form;
	}
	
	@PostMapping("/delete")
	public String delete(
			@RequestParam("id") String id, Model model, RedirectAttributes redirectAttributes) {
		service.deleteDatabaseById(Integer.parseInt(id));
		redirectAttributes.addFlashAttribute("delcomplete", "削除が完了しました。");
		return "redirect:/database";
	}
	
	@PostMapping("/save")
	public String save() throws IOException {
		String sql = "SELECT * FROM database";
		//jdbc:<subprotocol>:<subname>
		String url = "jdbc:postgresql://localhost:5432/testdb";
		File backup = new File("backup.txt");
		if(backup.exists()) {
			//"backup.txt"の削除
			backup.delete();	
		}
		//新しいbackup.txtの生成
		backup.createNewFile();
		
		List<Database> list = new ArrayList<>();
		
		//getConnection(url, "username", "password")
		try(Connection con = DriverManager.getConnection(url, "postgres", "postgres");
			PreparedStatement ps = con.prepareStatement(sql);
			BufferedWriter  bw = new BufferedWriter(new FileWriter(backup))) {
			
			 ResultSet rs = ps.executeQuery();
			 //テーブルの最終行までデータ抽出を繰り返す
			while(rs.next()) {
				Database data = new Database();
				data.setId(rs.getInt(1));
				data.setName(rs.getString(2));
				data.setAge(rs.getInt(3));
				//中間物としてリストを生成しておくとStreamにもかけられて便利
				list.add(data);
			}
			
			//例：ageの降順に並べ替える
			//list = list.stream()
			//		.sorted(Comparator.comparing(Person::getAge).reversed())
			//		.collect(Collectors.toList());
			
			for(int i=0; i<list.size(); i++) {
				//backup.txtにDBのデータを抽出
				bw.write(list.get(i).getId() + "     " 
						+ list.get(i).getName() + "     " 
							+ list.get(i).getAge() + "\n");
			}
			
		} catch(SQLException e) { e.printStackTrace(); }
		
		
		
		
		return "redirect:/database";
	}
}
