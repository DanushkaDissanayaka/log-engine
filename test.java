mBluetoothLe.setScanPeriod(10000)
                .setReportDelay(0)
                .startScan(this.getActivity().getBaseContext(), new OnLeScanListener() {
                    @Override
                    public void onScanResult(BluetoothDevice bluetoothDevice, int rssi, ScanRecord scanRecord) {
                        Log.d(TAG, "onScanResult: Found device");

                        BLEDevice device = new BLEDevice();
                        device.mDeviceAddress = bluetoothDevice.getAddress();
                        if (ActivityCompat.checkSelfPermission(BluetoothSDKPlugin.this.getActivity().getBaseContext(), Manifest.permission.BLUETOOTH_ADMIN) != PackageManager.PERMISSION_GRANTED) {
                            Log.d("PERMISSION", "BLUETOOTH_CONNECT Permission not granted");
                            return;
                        }
                        device.mDeviceName = bluetoothDevice.getName();
                        device.mRssi = rssi;
                        device.setmBytes(scanRecord.getBytes());
                        device.setParcelId(scanRecord.getParcelId());



                        Log.d("BLE ON SCAN Device" , bluetoothDevice.getName());
                        if(!showList.contains(device)){
                            showList.add(device);
                            Collections.sort(showList);
                        }

                    }

                    @Override
                    public void onBatchScanResults(List<ScanResult> results) {
                        Log.i(TAG, "ONBATCHSCANRESULTS：" + results.toString());
                    }

                    @Override
                    public void onScanCompleted() {
                        mBluetoothLe.stopScan();
                    }

                    @Override
                    public void onScanFailed(ScanBleException e) {

                        if((long) showList.size() > 0){
                            List<Map> bleList = new ArrayList<>();
                            showList.forEach(d -> {
                                Map<String, String> map = new HashMap<String, String>();
                                map.put("name", d.mDeviceName);
                                map.put("mac", d.mDeviceAddress);
                                bleList.add(map);
                            });

                            JSONArray jsonArray = new JSONArray(bleList);
                            ret.put("msg", "Device Found ");
                            ret.put("error", false);
                            ret.put("results", jsonArray);
                            call.resolve(ret);
                        } else {
                            Log.e(TAG, "Error ：" + e.toString());
                            ret.put("msg", "Error " + e.toString());
                            ret.put("error", true);
                            call.resolve(ret);
                        }

                        mBluetoothLe.stopScan();

                    }
                });
