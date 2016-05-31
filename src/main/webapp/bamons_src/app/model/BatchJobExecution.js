Ext.define('SpringBatch.model.BatchJobExecution', {
    extend: 'Ext.data.Model',
    alias: 'model.BatchJobExecution',
    fields: [
	    {name: 'jobExecutionId', type: 'int'},
	    {name: 'jobName', type: 'string'},
	    {name: 'jobInstanceId', type: 'int'},
	    {name: 'startTime', type: 'string'},
	    {name: 'endTime', type: 'string'},
        {name: 'status', type: 'string'},
        {name: 'exitCode', type: 'string'}
    ]
});