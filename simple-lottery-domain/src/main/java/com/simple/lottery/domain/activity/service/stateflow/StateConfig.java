package com.simple.lottery.domain.activity.service.stateflow;

import com.simple.lottery.common.enums.ActivityState;
import com.simple.lottery.domain.activity.service.stateflow.event.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 项目: simple-lottery
 * <p>
 * 功能描述: 状态流转配置
 *
 * @author: WuChengXing
 * @create: 2022-08-01 00:01
 **/
public class StateConfig {

    @Resource
    private ArraignmentState arraignmentState;
    @Resource
    private CloseState closeState;
    @Resource
    private DoingState doingState;
    @Resource
    private EditingState editingState;
    @Resource
    private OpenState openState;
    @Resource
    private PassState passState;
    @Resource
    private RefuseState refuseState;

    protected Map<Enum<ActivityState>, AbstractState> stateGroup = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        stateGroup.put(ActivityState.ARRAIGNMENT, arraignmentState);
        stateGroup.put(ActivityState.CLOSE, closeState);
        stateGroup.put(ActivityState.DOING, doingState);
        stateGroup.put(ActivityState.EDIT, editingState);
        stateGroup.put(ActivityState.OPEN, openState);
        stateGroup.put(ActivityState.PASS, passState);
        stateGroup.put(ActivityState.REFUSE, refuseState);
    }

}
