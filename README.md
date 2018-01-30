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
        public void loadNetData1(){
                final Random rd = new Random();
                showLoading(true);//true代表加载中的视图背景是透明的
                requestData(apiService.getPageData(), new NetRequestWork.OnRequestListener<UgouCountBean>() {
                    @Override
                    public void onRecvDataBack(final UgouCountBean ugouCountBean) {
                        BaseApplication.mhandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                int i = rd.nextInt(10);
                                if(i>0 && i<=3){////假设1~3网络数据请求成功
                                    Toast.makeText(MainActivity.this,ugouCountBean.toString(),Toast.LENGTH_SHORT).show();
                                    hideLoding();
                                }else if(i>3 && i<=6){//4~6服务器报异常
                                    //throw new CustomDataException("模拟异常~~");
                                    hideLoding();
                                    showErrorView();
                                }else{//其它为服务器返回空数据
                                    hideLoding();
                                    showNoDataView();
                                }
                            }
                        },2000);
                    }
                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                        hideLoding();
                        showErrorView();
                    }
                });
        }


####程序员只需要在适当的位置 该显示什么视图就显示什么视图 不需要考虑其它（如 加载失败重新加载...） 程序员精力只要放在数据成功返回处理好数据怎么显示就可以了
