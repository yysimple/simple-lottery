package com.simple.lottery.infrastructure.repository;

import com.simple.lottery.domain.strategy.model.aggregates.StrategyRich;
import com.simple.lottery.domain.strategy.model.vo.AwardBriefVO;
import com.simple.lottery.domain.strategy.model.vo.StrategyBriefVO;
import com.simple.lottery.domain.strategy.model.vo.StrategyDetailBriefVO;
import com.simple.lottery.domain.strategy.repository.IStrategyRepository;
import com.simple.lottery.infrastructure.entity.Award;
import com.simple.lottery.infrastructure.entity.Strategy;
import com.simple.lottery.infrastructure.entity.StrategyDetail;
import com.simple.lottery.infrastructure.mapper.AwardMapper;
import com.simple.lottery.infrastructure.mapper.StrategyDetailMapper;
import com.simple.lottery.infrastructure.mapper.StrategyMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 策略表仓储服务
 *
 * @author: WuChengXing
 * @create: 2022-07-28 21:12
 **/
@Repository
public class StrategyRepository implements IStrategyRepository {

    @Resource
    private StrategyMapper strategyMapper;

    @Resource
    private StrategyDetailMapper strategyDetailMapper;

    @Resource
    private AwardMapper awardMapper;

    @Override
    public StrategyRich queryStrategyRich(Long strategyId) {
        Strategy strategy = strategyMapper.queryStrategy(strategyId);
        List<StrategyDetail> strategyDetailList = strategyDetailMapper.queryStrategyDetailList(strategyId);

        StrategyBriefVO strategyBriefVO = new StrategyBriefVO();
        strategyBriefVO.setStrategyId(strategy.getStrategyId());
        strategyBriefVO.setStrategyDesc(strategy.getStrategyDesc());
        strategyBriefVO.setStrategyMode(strategy.getStrategyMode());
        strategyBriefVO.setGrantType(strategy.getGrantType());
        strategyBriefVO.setGrantDate(strategy.getGrantDate());
        strategyBriefVO.setExtInfo(strategy.getExtInfo());

        List<StrategyDetailBriefVO> strategyDetailBriefVOList = new ArrayList<>();
        for (StrategyDetail strategyDetail : strategyDetailList) {
            StrategyDetailBriefVO strategyDetailBriefVO = new StrategyDetailBriefVO();
            strategyDetailBriefVO.setStrategyId(strategyDetail.getStrategyId());
            strategyDetailBriefVO.setAwardId(strategyDetail.getAwardId());
            strategyDetailBriefVO.setAwardName(strategyDetail.getAwardName());
            strategyDetailBriefVO.setAwardCount(strategyDetail.getAwardCount());
            strategyDetailBriefVO.setAwardSurplusCount(strategyDetail.getAwardSurplusCount());
            strategyDetailBriefVO.setAwardRate(strategyDetail.getAwardRate());
            strategyDetailBriefVOList.add(strategyDetailBriefVO);
        }

        return new StrategyRich(strategyId, strategyBriefVO, strategyDetailBriefVOList);
    }

    @Override
    public AwardBriefVO queryAwardInfo(String awardId) {

        Award award = awardMapper.queryAwardInfo(awardId);

        AwardBriefVO awardBriefVO = new AwardBriefVO();
        awardBriefVO.setAwardId(award.getAwardId());
        awardBriefVO.setAwardType(award.getAwardType());
        awardBriefVO.setAwardName(award.getAwardName());
        awardBriefVO.setAwardContent(award.getAwardContent());

        return awardBriefVO;
    }

    @Override
    public List<String> queryNoStockStrategyAwardList(Long strategyId) {
        return strategyDetailMapper.queryNoStockStrategyAwardList(strategyId);
    }

    @Override
    @Transactional
    public boolean deductStock(Long strategyId, String awardId) {
        //先通过策略id+奖品id加锁记录
        StrategyDetail strategyDetail = strategyDetailMapper.getAndLockByStrategyIdAndAwardId(strategyId, awardId);
        if (null != strategyDetail && !Objects.isNull(strategyDetail.getAwardSurplusCount()) && strategyDetail.getAwardSurplusCount() > 0) {
            strategyDetail.setAwardSurplusCount(strategyDetail.getAwardSurplusCount() - 1);
            strategyDetail.setUpdateTime(new Date());
            int count = strategyDetailMapper.deductStock(strategyDetail);
            return count == 1;
        }
        return false;

    }
}
