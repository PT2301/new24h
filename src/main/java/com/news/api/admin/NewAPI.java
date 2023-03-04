package com.news.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.news.dto.NewOutput;
import com.news.dto.NewDTO;
import com.news.service.impl.NewService;


@RestController  //biến 1 class bt thành webservice 
public class NewAPI {
	@Autowired
	private NewService newService;


	 @GetMapping(value="/search")
	  public NewOutput searchNew(@RequestParam(value="page",required=false) Integer page,
			  					@RequestParam(value="limit",required=false) Integer limit,
			  					@RequestParam(value="key",required=false) String key) {
		  NewOutput result=new NewOutput();
		  if(key==null) {
			  result.setPage(page);
			  result.setTotalPage((int) Math.ceil((double) (newService.totalItem()) / limit));
			  Pageable pageable = PageRequest.of(page-1, limit);
			  result.setListResult(newService.itemOnePage(pageable));
		  }else {			 
			  result.setPage(page);
			  result.setTotalPage((int) Math.ceil((double) (newService.totalSearchNew(key).size()) / limit));
			  Integer offset=(page-1)*limit;
			  result.setListResult(newService.searchNew(key, offset,limit));
		  }
		  return result;
	 }
  @GetMapping(value="/new")
	  public NewOutput showNew(@RequestParam(value="page",required=false) Integer page,
			  					@RequestParam(value="limit",required=false) Integer limit) {
		  NewOutput result=new NewOutput();
		  if(page!=null&&limit!=null) {
			  result.setPage(page);
			  result.setTotalPage((int) Math.ceil((double) (newService.totalItem()) / limit));
			  Pageable pageable = PageRequest.of(page-1, limit);
			  result.setListResult(newService.itemOnePage(pageable));
		  }else {
			  result.setListResult(newService.findAll());
		  }
		  return result;
  }
  @RequestMapping(value="/new",method=RequestMethod.POST)
  public NewDTO createNew(@RequestBody NewDTO model) {
	  NewDTO news=newService.save(model);
	  return news;
  }
  @PutMapping(value="/new/{id}")
  public NewDTO updataNew(@RequestBody NewDTO model,@PathVariable Long id) {
	  model.setId(id);
	  NewDTO news=newService.save(model);
	  return news;
  }
  @DeleteMapping(value="/new")
  public void deleteNew(@RequestBody Long[] ids) {
	  newService.delete(ids);
  }

}