package com.news.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.news.dto.NewDTO;
import com.news.entity.CategoryEntity;
import com.news.entity.NewEntity;
import com.news.repository.CategoryRepository;
import com.news.repository.NewRepository;
import com.news.service.INewService;
@Service
public class NewService implements INewService{
	@Autowired
	private NewRepository newRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public NewDTO save(NewDTO newDTO) {
		if(newDTO.getId()!=null) {
			newDTO.setCreatedDate(newRepository.findById(newDTO.getId()).get().getCreatedDate());
		}
		CategoryEntity category=categoryRepository.findOneByCode(newDTO.getCategoryCode());
		NewEntity news = modelMapper.map(newDTO, NewEntity.class);
		news.setCategory(category);
		news=newRepository.save(news);
		return modelMapper.map(news, NewDTO.class) ;
	}
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids) {
			newRepository.deleteById(id);
		}
		
	}
	@Override
	public int totalItem() {
		int x=(int)newRepository.count();
		return x;
	}
	@Override
	public List<NewDTO> itemOnePage(Pageable pageable) {
		List<NewDTO> result=new ArrayList<>();
		List<NewEntity> list=newRepository.findAll(pageable).getContent();
		for(NewEntity item:list) {
			result.add(modelMapper.map(item,NewDTO.class));
		}
		return result;
	}
	@Override
	public List<NewDTO> findAll() {
		List<NewDTO> result=new ArrayList<>();
		List<NewEntity> list=newRepository.findAll();
		for(NewEntity item:list) {
			result.add(modelMapper.map(item,NewDTO.class));
		}
		return result;
	}
	@Override
	public List<NewDTO> searchNew(String key, Integer offset,Integer limit) {
		List<NewDTO> result=new ArrayList<>();
		List<NewEntity> list=newRepository.getTotalSearch(key, offset,limit);
		for(NewEntity item:list) {
			result.add(modelMapper.map(item,NewDTO.class));
		}
		return result;
	}
	@Override
	public List<NewDTO> totalSearchNew(String key) {
		List<NewDTO> result=new ArrayList<>();
		List<NewEntity> list=newRepository.totalSearchNew(key);
		for(NewEntity item:list) {
			result.add(modelMapper.map(item,NewDTO.class));
		}
		return result;
	}
}
