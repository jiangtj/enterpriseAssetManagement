package com.jtj.web.service.base;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2017/10/15 22:27 End.
 */
public interface TreeService<T extends TreeEntity> {

    List<T> getTreeResource();

    default List<T> getTreeListResource(){
        return null;
    }
    default Map<Long,T> getTreeMapResource(){
        return null;
    }

    default void refreshTreeData(){
        if (getTreeListResource() != null) getTreeListResource().clear();
        if (getTreeMapResource() != null) getTreeMapResource().clear();
    }

    default boolean isEmpty(List<?> list){
        return CollectionUtils.isEmpty(list);
    }
    default boolean isEmpty(Map<Long,?> map){
        return CollectionUtils.isEmpty(map);
    }
    default boolean isNotEmpty(List<?> list){
        return !isEmpty(list);
    }
    default boolean isNotEmpty(Map<Long,?> map){
        return !isEmpty(map);
    }

    default List<T> getTreeList(){
        if (isNotEmpty(getTreeListResource()))
            return getTreeListResource();
        List<T> list = getTreeResource().stream()
                .peek(item -> item.setNodes(new ArrayList<>()))
                .collect(Collectors.toList());
        if (getTreeListResource() != null && isNotEmpty(list))
            synchronized (this){
                if (getTreeListResource().size() == 0)
                    getTreeListResource().addAll(list);
            }
        return list;
    }

    default Map<Long,T> getTreeMap(){
        if (isNotEmpty(getTreeMapResource()))
            return getTreeMapResource();
        List<T> treeList = getTreeList();
        Map<Long,T> temp = treeList.stream().collect(Collectors.toMap(TreeEntity::getId, y->y));
        treeList.forEach(tree -> {
            if (Objects.equals(tree.getPid(), tree.getId()) || tree.getPid() == 0) {
                return;
            }
            T p = temp.get(tree.getPid());
            p.getNodes().add(tree);
        });
        if (getTreeMapResource() != null && isNotEmpty(treeList))
            synchronized (this){
                if (getTreeMapResource().size() == 0){
                    getTreeMapResource().putAll(temp);
                }
            }
        return temp;
    }
    default List<T> getTreeRoot(){
        return getTreeList().stream()
                .filter(item -> Objects.equals(item.getPid(), item.getId()) || item.getPid() == 0)
                .collect(Collectors.toList());
    }

}
