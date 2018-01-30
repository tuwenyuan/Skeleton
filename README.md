# Skeleton
android 项目基本框架搭建 新版

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
        showLoading(true);
        requestData(apiService.getPageData(), new NetRequestWork.OnRequestListener<UgouCountBean>() {
            @Override
            public void onRecvDataBack(final UgouCountBean ugouCountBean) {
                BaseApplication.mhandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        int i = rd.nextInt(10);
                        if(i>0 && i<=3){
                            Toast.makeText(MainActivity.this,ugouCountBean.toString(),Toast.LENGTH_SHORT).show();
                            hideLoding();
                        }else if(i>3 && i<=6){
                            //throw new CustomDataException("模拟异常~~");
                            hideLoding();
                            showErrorView();
                        }else{
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
