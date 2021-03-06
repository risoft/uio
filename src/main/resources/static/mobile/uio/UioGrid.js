Ext.define('uio.UioGrid', {
    extend: 'Ext.dataview.List',
    requires: ['uio.UioRecordViewer'],
 
    xtype: 'uiogrid',
    fullscreen: true,
    disclosureProperty: 'name',
    onItemDisclosure: true,
  
    
    detailCard: {
        xtype: 'container',
        layout: 'fit'
    },

    
    select: function(record, arg2, arg3)
    {
    	var recordViewer = Ext.create("uio.UioRecordViewer", {jclass:this.config.jclass, data: record.data, layout:'fit'});
    	Ext.Viewport.add(recordViewer);
    	recordViewer.show();
    	this.callParent(arguments);
    },
    
    constructor: function(config)
    {
    	config.store = {
            fields: config.fields || uio.formdefs[config.jclass].storeFields,
            autoLoad: true,
            proxy: {
            	type: 'ajax',
            	url: config.url || '../../'+config.jclass+'/list.json'}
        };  
    	config.autoLoad =true;
        config.itemTpl=  config.itemTpl || '{name}';

    	config.disclosureProperty= config.disclosureProperty || 'name';
        config.onItemDisclosure={scope: this,handler: function(record, button, index){this.select(record, button, index)}};
        
     	this.callParent(arguments);
    }
});
