package com.horus.junior_java_developer_horus;

import java.util.List;

interface CompositeBlock extends Block {
    List<Block> getBlocks();
}