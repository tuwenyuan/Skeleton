# Skeleton
android 项目基本框架搭建 新版

![gif图](https://github.com/tuwenyuan/Skeleton/blob/master/git/a1.gif)

        @Override
        public void initListener() {
            binding.tvLoad.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startRequestNetData(new IRequestNetData() {
                        @Override
                        public void loadNetData() {
                            loadNetData1();
                        }
                    });
                }
            });
        }


####程序员只需要在适当的位置 该显示什么视图就显示什么视图 不需要考虑其它（如 加载失败重新加载...） 程序员精力只要放在数据成功返回处理好数据怎么显示就可以了
