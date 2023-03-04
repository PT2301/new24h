package com.news.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.news.dto.NewDTO;

@Service
public interface INewService {
	NewDTO save(NewDTO model);
	void delete(Long[] ids);
	int totalItem();
	List<NewDTO> itemOnePage(Pageable pageable);
	List<NewDTO> findAll();
	List<NewDTO> searchNew(String key,Integer offset,Integer limit);
	List<NewDTO> totalSearchNew(String key);

}
