Ext.define('SpringBatch.view.main.StepInfo', {

    extend: 'Ext.window.Window',
    controller: 'main',

    requires: [
        'Ext.panel.Panel'
    ],

	bind: {
		title: '{stepColumn} 정보'
	},

	frame: true,
    width: 500,
	height: 300,
    bodyPadding: 5,
    modal : true,
	resizable: false,
	layout   : 'fit',
    initComponent : function() {

		this.items =  [{

                defaultType: 'displayfield',
                defaults: {
                    labelWidth: 100,
					anchor: '100%',
                    layout: {
                        type: 'hbox',
                        defaultMargins: {top: 0, right: 0, bottom: 0, left: 0}
                    }
                },
                items: [
                    {
                       xtype : 'textareafield',
					   width : '100%',
					   height : '100%',
					   grow      : true,
					   bind: '{stepRecord}'
                    }

                ]
            }
        ];
        this.callParent(arguments);
    }
});