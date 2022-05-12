package com.kh.cafesurfer.domain.searchBar.svc;

import com.kh.cafesurfer.domain.searchBar.SearchBar;
import com.kh.cafesurfer.domain.searchBar.dao.SearchBarDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchBarSVCImpl implements SearchBarSVC{

  private final SearchBarDAO searchBarDAO;

  @Override
  public List<SearchBar> hashTagAll() {
    return searchBarDAO.hashTagAll();
  }

  @Override
  public List<SearchBar> coffeeHashTagAll() {
    return searchBarDAO.coffeeHashTagAll();
  }

  @Override
  public List<SearchBar> beverageHashTagAll() {
    return searchBarDAO.beverageHashTagAll();
  }

  @Override
  public List<SearchBar> dessertHashTagAll() {
    return searchBarDAO.dessertHashTagAll();
  }

  @Override
  public List<SearchBar> goodsHashTagAll() {
    return searchBarDAO.goodsHashTagAll();
  }

  @Override
  public List<SearchBar> moodHashTagAll() {
    return searchBarDAO.moodHashTagAll();
  }

  @Override
  public List<SearchBar> viewHashTagAll() {
    return searchBarDAO.viewHashTagAll();
  }

  @Override
  public List<SearchBar> locHashTagAll() {
    return searchBarDAO.locHashTagAll();
  }

  @Override
  public void plusHahTagSearchCount(Long hashTagId) {
    searchBarDAO.plusHahTagSearchCount(hashTagId);
  }

  @Override
  public void minusHahTagSearchCount(Long hashTagId) {
    searchBarDAO.minusHahTagSearchCount(hashTagId);
  }
}
