Ext.define('SpringBatch.store.JobNameStore', {

    extend: 'Ext.data.Store',
    
	fields: [
        'optionText',
		'optionValue'
    ],
	
    alias: 'store.JobNameStore',
    autoLoad: false,
	storeId: 'JobNameStore',
    proxy: {
        type: 'ajax',
        api: {
        	read: '/jobName/list.do'
        },
        //select option
        reader: {
            //type: 'array',
            successProperty: 'success',
            rootProperty: 'data'
        }
    }
});