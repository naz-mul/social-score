package me.nalam.socialscore.services;

import me.nalam.socialscore.dao.SocialScoreDao;
import me.nalam.socialscore.entities.SocialScore;

import java.util.List;
import java.util.Optional;

public class SocialScoreService {
    private static SocialScoreDao socialScoreDao;

    public SocialScoreService() {
        socialScoreDao = new SocialScoreDao();
    }

    public void persist(SocialScore entity) {
        socialScoreDao.openCurrentSessionWithTransaction();
        socialScoreDao.save(entity);
        socialScoreDao.closeCurrentSessionWithTransaction();
    }

    public void deleteByUrl(String url) {
        socialScoreDao.openCurrentSessionWithTransaction();
        Optional<SocialScore> socialScore = socialScoreDao.get(url);
        socialScore.ifPresent(score -> socialScoreDao.delete(score));
        socialScoreDao.closeCurrentSessionWithTransaction();
    }

    public List<SocialScore> findAll() {
        socialScoreDao.openCurrentSession();
        List<SocialScore> socialScoreList = socialScoreDao.getAll();
        socialScoreDao.closeCurrentSession();
        return socialScoreList;
    }

}
