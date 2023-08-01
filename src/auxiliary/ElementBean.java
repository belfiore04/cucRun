package auxiliary;

import auxiliary.service.*;
import base.IBaseService;

public enum ElementBean {
    BackGround(new BackgroundService()),
    Gravity(new GravityService()),
    Terrain(new TerrainService()),
    Interactive(new InteractiveElementService()),

    NPC(new NPCService());
    private ElementBean(IBaseService service){
        this.service = service;
    }
    private IBaseService service;
    public IBaseService getService(){
        return service;
    }
    public static void init() {
        CommonConstants.TIMER_STOP = false;
        for(ElementBean elementBean : ElementBean.values()){
                elementBean.getService().init();
        }
    }
}
