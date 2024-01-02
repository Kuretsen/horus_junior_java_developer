package com.horus.junior_java_developer_horus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Wall implements Structure {
    private List<Block> blocks;

    public Wall() {
        this.blocks = new ArrayList<>();
    }

    //Required for testing
    public void addBlock(Block block) {
        blocks.add(block);
    }

    //Required for testing
    public void addCompositeBlock(CompositeBlock compositeBlock) {
        blocks.addAll(compositeBlock.getBlocks());
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        for (Block block : blocks) {
            if (block.getColor().equals(color)) {
                return Optional.of(block);
            } else if (block instanceof CompositeBlock) {
                Optional<Block> compositeBlockResult = ((CompositeBlock) block)
                        .getBlocks()
                        .stream()
                        .filter(b -> b.getColor().equals(color))
                        .findFirst();

                if (compositeBlockResult.isPresent()) {
                    return compositeBlockResult;
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        List<Block> result = new ArrayList<>();
        for (Block block : blocks) {
            if (block.getMaterial().equals(material)) {
                result.add(block);
            } else if (block instanceof CompositeBlock) {
                List<Block> compositeBlocks = ((CompositeBlock) block)
                        .getBlocks()
                        .stream()
                        .filter(b -> b.getMaterial().equals(material))
                        .toList();

                result.addAll(compositeBlocks);
            }
        }
        return result;
    }

    @Override
    public int count() {
        int count = blocks.size();
        for (Block block : blocks) {
            if (block instanceof CompositeBlock) {
                count += ((CompositeBlock) block).getBlocks().size();
            }
        }
        return count;
    }
}
