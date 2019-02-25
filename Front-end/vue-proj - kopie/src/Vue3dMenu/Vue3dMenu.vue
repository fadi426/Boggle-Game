<template>
    <div class="vue-3d-menu">
        <div :style="{transform:`rotate3d(0,1,0,${offsetY}deg)`}" @mouseenter="handleEnter" @mouseleave="handleLeave" @mousemove="handleMove" class="menu-wrap">
            <div class="vue-3d-menu-title">Menu</div>
            <menu-item :itemInfo="itemList[0]"></menu-item>
        </div>
    </div>
</template>

<script>
import MenuItem from "./MenuItem";
import ItemInfo from './lib/ItemInfo';
import Rotate from './lib/Rotate';

export default {
    data() {
        return {
        menuItems: [
			{ title: 'Home', click: ()=> this.$router.push('/') },
			{ title: 'Singleplayer', click: ()=> this.$router.push('/singleplayer') },
			{ title: 'Multiplayer', click: ()=> this.$router.push('/gamerooms') },
		],
            offsetY: 0,
            rotate: null
        };
    },

    computed: {
        itemList() {
            let list = this.menuItems.map(({ title, click }) => new ItemInfo(title, click));
            list.reduce((prev, current) => {
                prev && (prev.next = current);
                current && (current.prev = prev);
                return current;
            });
            return list;
        }
    },

    methods: {
        handleMove({ offsetX }) {
            let offset = (offsetX - 146 / 2) * 0.5;
            this.offsetY = offset;
        },
        handleEnter() {
            this.rotate.goDown();
        },
        handleLeave() {
            this.rotate.goUp();
        }
    },

    created() {
        this.rotate = new Rotate(this.itemList);
    },

    components: {
        MenuItem
    }
}
</script>

<style lang="scss" scoped>
.vue-3d-menu {
    position: relative;
    display: inline-block;
    box-sizing: border-box;
    width: 250px;
    height: 300px;
    perspective: 800px;
    transform-style: preserve-3d;

    .menu-wrap {
        transform-style: preserve-3d;
        z-index: 999;
        transform: rotateY(-30deg);

        .vue-3d-menu-title {
            background: url(./assets/topMenukopie.png) no-repeat;
            transform: translateZ(1px);
            padding-top: 22px;
            line-height: 75px;
            text-indent: 20px;
            font-size: 20px;
            color: #fff;
            font-weight: bold;
            z-index: 999;
        }
    }
}
</style>
