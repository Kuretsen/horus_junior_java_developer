package com.horus.junior_java_developer_horus;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class WallTestSuite {

    @Test
    void testFindBlockByColor() {
        // Given
        Wall wall = new Wall();
        Block block1 = new BlockImpl("red", "brick");
        Block block2 = new BlockImpl("blue", "wood");
        CompositeBlock compositeBlock = new CompositeBlockImpl(Arrays.asList(block1, block2));
        wall.addCompositeBlock(compositeBlock);

        // When
        Optional<Block> foundRedBlock = wall.findBlockByColor("red");
        Optional<Block> foundBlueBlock = wall.findBlockByColor("blue");

        // Then
        assertTrue(foundRedBlock.isPresent());
        assertEquals(block1, foundRedBlock.get());

        assertTrue(foundBlueBlock.isPresent());
        assertEquals(block2, foundBlueBlock.get());
    }

    @Test
    void testFindBlocksByMaterial() {
        // Given
        Wall wall = new Wall();
        Block block1 = new BlockImpl("blue", "brick");
        Block block2 = new BlockImpl("green", "wood");
        Block block3 = new BlockImpl("red", "brick");
        CompositeBlock compositeBlock = new CompositeBlockImpl(Arrays.asList(block1, block2));
        wall.addCompositeBlock(compositeBlock);
        wall.addBlock(block3);

        // When
        List<Block> foundWoodBlocks = wall.findBlocksByMaterial("wood");
        List<Block> foundBrickBlocks = wall.findBlocksByMaterial("brick");

        // Then
        assertEquals(1, foundWoodBlocks.size());
        assertEquals(2, foundBrickBlocks.size());
        assertTrue(foundBrickBlocks.contains(block1));
        assertTrue(foundWoodBlocks.contains(block2));
        assertTrue(foundBrickBlocks.contains(block3));
    }

    @Test
    void testCount() {
        // Given
        Wall wall = new Wall();
        Block simpleBlock = new BlockImpl("yellow", "stone");
        wall.addBlock(simpleBlock);

        CompositeBlock compositeBlock = new CompositeBlockImpl(Arrays.asList(
                new BlockImpl("green", "wood"),
                new BlockImpl("blue", "wood")
        ));
        wall.addCompositeBlock(compositeBlock);

        // When
        int count = wall.count();

        // Then
        assertEquals(3, count);
    }

    // Implementation Block
    static class BlockImpl implements Block {
        private final String color;
        private final String material;

        public BlockImpl(String color, String material) {
            this.color = color;
            this.material = material;
        }

        @Override
        public String getColor() {
            return color;
        }

        @Override
        public String getMaterial() {
            return material;
        }
    }

    // Implementation CompositeBlock
    static class CompositeBlockImpl implements CompositeBlock {
        private final List<Block> blocks;

        public CompositeBlockImpl(List<Block> blocks) {
            this.blocks = blocks;
        }

        @Override
        public List<Block> getBlocks() {
            return blocks;
        }

        @Override
        public String getColor() {
            List<String> colors = blocks.stream()
                    .map(Block::getColor)
                    .distinct()
                    .toList();

            if (colors.size() == 1) {
                return colors.get(0);
            } else {
                return "Multiple colors";
            }
        }

        @Override
        public String getMaterial() {
            List<String> materials = blocks.stream()
                    .map(Block::getMaterial)
                    .distinct()
                    .toList();

            if (materials.size() == 1) {
                return materials.get(0);
            } else {
                return "Multiple materials";
            }
        }
    }
}