Ext.define('SpringBatch.view.main.MainController', {

    extend: 'Ext.app.ViewController',
    alias: 'controller.main',

    jobStepInfo: function (record) {
        var win = Ext.create('SpringBatch.view.main.JobStepInfo', {
            viewModel: {
                data: {
                   jobStepRecord: record
                }
            }
        });
        win.show();
    },

	stepInfo: function (record) {
        var win = Ext.create('SpringBatch.view.main.StepInfo', {
            viewModel: {
                data: {
                   stepRecord: record
                }
            }
        });
        win.show();
    },

    onItemSelected: function (sender, record) {
        this.jobStepInfo(record);
    },

	onStepItemSelected: function (sender, record) {
        this.stepInfo(record);
    },

	onItemClick: function(view, rowIdx, colIdx, item, e, record) {
		this.jobStepInfo(record);
    },

    onItemDblClick: function(view, record) {
        this.jobStepInfo(record);
    },

	// 특정Job 구동팝업
    onStartWinClick: function () {
        var win = Ext.create('SpringBatch.view.main.JobStart',{});
        win.show();
    },

	// 리스트 초기화
	onSearchResetClick: function () {

		this.lookupReference('startTime').reset();
		var store = Ext.data.StoreManager.lookup('BatchJobExecutionStore');
		store.currentPage = 1;
		store.load({
			params: {
				startTime : '0'
			},
			callback: function (records) {
				store.proxy.extraParams.startTime = '0';
			}
		});
    },

	// 검색
	onSearchClick: function () {
		var form = this.lookupReference('startTime');

		if(form.isValid()) {
			var startTime = Ext.Date.format(this.lookupReference('startTime').getValue(),'Y-m-d');

			if(Ext.isEmpty(startTime)) {
				Ext.MessageBox.alert('확인', '시작 시간을 선택하여 주세요');
			}else{
				var store = Ext.data.StoreManager.lookup('BatchJobExecutionStore');
				store.load({
					params: {
						startTime : startTime,
						start : 0,
						page : 1
					},
					callback: function (records) {
						//Ext.getCmp('jobExecutionId').setValue(store.getAt(0).data.jobExecutionId);
						store.proxy.extraParams.startTime = startTime;
					}
				});
			}
		}
    },

	// Job 구동
	onStartClick: function () {

		// 방법 1
		var form = this.lookupReference('jobForm');
		var targetDate = '';
		var filePath = '';
		var jobName = '';
		var ajaxURL = '';

		if(form.isValid()) {

			var encode = Ext.String.htmlEncode;

			console.log(encode);
			
			Ext.iterate(form.getValues(), function(key, value) {
				key = encode(key);
				value = encode(value);

				if(key == 'targetDate') {
					targetDate = value;
				}else if(key == 'filePath') {
					filePath = value;
				}else if(key == 'jobName') {
					jobName = value;
				}
			}, this);
			
			if(Ext.isEmpty(jobName)) {
				Ext.MessageBox.alert('확인', 'Job Name 을 선택하여 주세요.');
			}
			
			if(jobName == 'restoreRawDataJob') {
				if(Ext.isEmpty(filePath)) {
					Ext.MessageBox.alert('확인', '파일 경로를 입력하여 주세요');
				}
				
				ajaxURL = '/rawdata/restore.do';
				
			}else{
				if(Ext.isEmpty(targetDate)) {
					Ext.MessageBox.alert('확인', '통계날짜를 선택하여 주세요');
				}
				
				ajaxURL = '/job/start.do';
			}						

			Ext.MessageBox.confirm('확인', 'Job을 구동하시겠습니까?', function(btn){
				if (btn == 'yes') {

					Ext.MessageBox.show({
						msg : '잠시만 기다려 주세요...',
						width : 300,
						wait : true,
						waitConfig :
						{
							duration : 2000,
							increment : 2,
							text : 'Job 을 시작중입니다..',
							scope : this,
							fn : function(){
								Ext.MessageBox.hide();
								Ext.Msg.alert('상태', 'Job이 구동되었습니다. 리스트를 리프레쉬하세요');
							}
						}
					});

					Ext.Ajax.request({
						url: ajaxURL,
						method: 'GET',
						params: {
							jobName: jobName,
							targetDate: targetDate,
							filePath : filePath
						},
						success: function(res){
							var obj = Ext.JSON.decode(res.responseText);
							//console.log(obj.success);
							if(obj.status == false) {
								Ext.MessageBox.hide();
								Ext.Msg.alert('Job 구동에 실패하였습니다.', obj.statusMessage);
							}

						},
						failure: function(res){
							Ext.MessageBox.hide();
							Ext.Msg.alert('상태', 'Job 구동에 실패하였습니다.');
						}
					});
				}
			});
		}

		// 방법 2
		//var targetDate = Ext.Date.format(this.lookupReference('targetDate').getValue(),'Y-m-d');
		//var jobName = this.lookupReference('jobName').getValue();

		//if(Ext.isEmpty(targetDate)) {
		//	Ext.MessageBox.alert('확인', '통계날짜를 선택하여 주세요');
		//}else if(Ext.isEmpty(jobName)) {
		//	Ext.MessageBox.alert('확인', 'Job Name 을 선택하여 주세요.');
		//}

    },

    // Job 재구동
	onRestartClick: function () {

		var record = this.getViewModel().get('jobStepRecord');
		console.log(record.data.jobExecutionId);
		//console.log(rec);

		Ext.MessageBox.confirm('확인', 'Job을 구동하시겠습니까?', function(btn){
			if (btn == 'yes') {

				Ext.MessageBox.show({
					msg : '잠시만 기다려 주세요...',
					width : 300,
					wait : true,
					waitConfig :
					{
						duration : 2000,
						increment : 2,
						text : 'Job 을 시작중입니다..',
						scope : this,
						fn : function(){
							Ext.MessageBox.hide();
							Ext.Msg.alert('상태', 'Job이 구동되었습니다. 리스트를 리프레쉬하세요');
						}
					}
				});

				Ext.Ajax.request({
					url: '/job/restart.do',
					method: 'GET',
					params: {
						jobExecutionId: record.data.jobExecutionId,
						jobName: record.data.jobName
					},
					success: function(res){
						var obj = Ext.JSON.decode(res.responseText);
						//console.log(obj.success);
                        if(obj.status == false) {
                            Ext.MessageBox.hide();
                            Ext.Msg.alert('Job 구동에 실패하였습니다.', obj.statusMessage);
                        }

					},
					failure: function(res){
                        Ext.MessageBox.hide();
                        Ext.Msg.alert('상태', 'Job 구동에 실패하였습니다.');
					}
				});
			}
		});
    },

    // Job 중지
    onStopClick: function () {

        var record = this.getViewModel().get('jobStepRecord');
        console.log(record.data.jobExecutionId);
        //console.log(rec);

        Ext.MessageBox.confirm('확인', 'Job을 중지하시겠습니까?', function(btn){
            if (btn == 'yes') {

                Ext.MessageBox.show({
                    msg : '잠시만 기다려 주세요...',
                    width : 300,
                    wait : true,
                    waitConfig :
                    {
                        duration : 2000,
                        increment : 2,
                        text : 'Job 을 중지중입니다..',
                        scope : this,
                        fn : function(){
                            Ext.MessageBox.hide();
                            Ext.Msg.alert('상태', '리스트를 리프레쉬하세요');
                        }
                    }
                });

                Ext.Ajax.request({
                    url: '/job/stop.do',
                    method: 'GET',
                    params: {
                        jobExecutionId: record.data.jobExecutionId
                    },
                    success: function(res){
                        var obj = Ext.JSON.decode(res.responseText);
                        //console.log(obj.success);
                        if(obj.status == false) {
                            Ext.MessageBox.hide();
                            Ext.Msg.alert('Job 중지에 실패하였습니다.', obj.statusMessage);
                        }
                    },
                    failure: function(res){
                        Ext.MessageBox.hide();
                        Ext.Msg.alert('상태', 'Job 중지에 실패하였습니다.');
                    }
                });
            }
        });
    },

    onJobStepWinCloseClick: function (win) {
       win.up('window').close();
    },

	onStartWinCloseClick: function (win) {
       win.up('window').close();
    },

    onConfirm: function (choice) {
        if (choice === 'yes') {
            //
        }
    }
});