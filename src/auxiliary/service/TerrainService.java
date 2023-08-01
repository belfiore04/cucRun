package auxiliary.service;

import auxiliary.Generator.TerrainGenerator;
import base.BaseElement;
import base.BaseService;

import java.awt.*;

public class TerrainService extends BaseService<BaseElement> {
    @Override
    public void init(){
        getElementList().clear();
        TerrainGenerator.generateTerrain();
    }
    @Override
    public void drawImage(Graphics g){
        this.getElementList().forEach(i -> i.drawImage(g));
    }
}
